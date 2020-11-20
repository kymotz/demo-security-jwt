package com.elltor.security_jwt.config.security;

import com.elltor.security_jwt.config.security.handler.MyAuthencationFailureHandler;
import com.elltor.security_jwt.config.security.handler.MyAuthenticationSuccessHandler;
import com.elltor.security_jwt.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler successHandler;

    @Resource
    private MyAuthencationFailureHandler failureHandler;

    @Resource
    private UserDetailServiceImpl myUserDetailsService;

    /**
     * 当前配置为form表单登录认证方式
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//开启formlogin模式
            .loginPage("/login.html")//用户未登录时，访问任何资源跳转到该页面
            .loginProcessingUrl("/login")//登录表单form中action的地址，即处理认证的路径
            .usernameParameter("username")//默认是username,提交用户名字段
            .passwordParameter("password")//默认是password,提交密码字段
//            .defaultSuccessUrl("/index")//登录成功跳转接口
//            .failureUrl("/login")//登录失败跳转页面
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()// 连接, 在后面继续配置其他的
        .authorizeRequests()//配置登录后的权限
            .antMatchers("/login.html","/login")// 这两个页面等价, /login.html是直接访问页面, 而/login是通过Controller跳转到login.html页面
            .permitAll()//用户可任意访问
            .antMatchers("/order")//需对外保留的资源路径
            .hasAnyAuthority("ROLE_user","ROLE_admin")//user角色和admin角色可以访问
            .antMatchers("/system/user","/system/role","/system/menu")
//            .hasAnyRole("admin")//admin可访问
            .hasAnyAuthority("ROLE_admin")
            // 除上面外的请求全部需要鉴权认证------------------------
            .anyRequest().authenticated()//authenticated()要求在执行请求要求必须已登录
            .and()// 连接, 继续配置其他的
        .csrf().disable();//禁用跨站csrf攻击防御, 否则无法成功登录

        //登出功能
        http.logout().logoutUrl("/logout");
    }

    @Override
    public  void  configure(AuthenticationManagerBuilder  auth)  throws  Exception
    {
        //调用DetailsService完成用户身份验证              设置密码加密方式
        auth.userDetailsService(myUserDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

//    // 配置认证方法, http basic登录基础配置
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(getBCryptPasswordEncoder().encode("123456"))
//                .roles("ROLE_user")
//                .and()
//                .withUser("admin")
//                .password(getBCryptPasswordEncoder().encode("123456"))
//                .roles("ROLE_admin")
//                .and()
//                .passwordEncoder(getBCryptPasswordEncoder());//配置BCrypt加密
//    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

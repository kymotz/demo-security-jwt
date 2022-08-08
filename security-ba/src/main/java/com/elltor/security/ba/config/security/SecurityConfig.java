package com.elltor.security.ba.config.security;

import com.elltor.security.ba.service.impl.UserDetailsServiceImpl;
import com.elltor.security.ba.config.security.handler.ProjectAuthenticationFailureHandler;
import com.elltor.security.ba.config.security.handler.ProjectAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private ProjectAuthenticationSuccessHandler successHandler;

    @Resource
    private ProjectAuthenticationFailureHandler failureHandler;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private RedisSessionRegistry redisSessionRegistry;

    /**
     * 当前配置为form表单登录认证方式
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf、cors、click-jacking
        // 禁用跨站csrf攻击防御, 否则无法成功登录
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //登出功能
        http
                // 开启form-login模式
                .formLogin()
                // 用户未登录时，访问任何资源跳转到该页面
                .loginPage("/login.html")
                // 登录表单form中action的地址，即处理认证的路径
                .loginProcessingUrl("/login")
                // 默认是username,提交用户名字段
                .usernameParameter("username")
                //默认是password,提交密码字段
                .passwordParameter("password")
                // 等价 defaultSuccessUrl("/index")//登录成功跳转接口
                .successHandler(successHandler)
                // failureUrl("/login")//登录失败跳转页面
                .failureHandler(failureHandler)
                .and()
                .logout().logoutUrl("/logout")
                .and()
                // 配置登录后的权限，所有的规则 Filter 都被 FilterChainProxy 包含，每种匹配规则是一个 SecurityFilterChain
                .authorizeRequests()
                // 这两个页面等价, /login.html是直接访问页面, 而/login是通过Controller跳转到login.html页面
                // filter 1
                .antMatchers("/login.html", "/login")
                // 非认证用户可任意访问
                .anonymous()
                // filter 2
                .antMatchers("/order")//需对外保留的资源路径
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")//user角色和admin角色可以访问
                // filter 3
                .antMatchers("/system/user", "/system/role", "/system/menu")
//            .hasAnyRole("admin")//admin可访问
                .hasAnyAuthority("ROLE_ADMIN")
                // 除了上面的规则匹配外的，要求在执行请求要求必须已登录
                // filter 4
                .anyRequest()
                // 除上面外的请求全部需要鉴权认证------------------------
                .authenticated();
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/session/invalid")
//                .maximumSessions(1)
//                .sessionRegistry(redisSessionRegistry);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //调用DetailsService完成用户身份验证              设置密码加密方式
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setUserDetailsPasswordService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider);
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123456"))
//                .roles("ROLE_user")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("ROLE_admin")
//                .and()
//                .passwordEncoder(passwordEncoder());//配置BCrypt加密
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

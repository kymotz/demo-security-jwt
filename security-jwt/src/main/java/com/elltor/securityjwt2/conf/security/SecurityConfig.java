package com.elltor.securityjwt2.conf.security;

import com.elltor.securityjwt2.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl myUserDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 认证失败处理类
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session,这里设置STATELESS(无状态)是在请求是不生成session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //配置权限
                .authorizeRequests()
                //对于登录login  验证码captchaImage  允许匿名访问
                .antMatchers("/login").anonymous().antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll().antMatchers("/order")  //需要对外暴露的资源路径
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")    //user角色和 admin角色都可以访问
                .antMatchers("/system/user", "/system/role", "/system/menu").hasAnyRole("ADMIN")    //admin角色可以访问
                //  除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and()//authenticated()要求在执 行该请求时，必须已经登录了应用
                //  CSRF禁用，因为不使用session
                .csrf().disable();//禁用跨站csrf攻击防御，否则无法登陆成功
        //登出功能
        http.logout().logoutUrl("/logout");
        //  添加JWT  filter, 在每次http请求前进行拦截
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //调用DetailsService完成用户身份验证              设置密码加密方式
        auth.userDetailsService(myUserDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }


    // 在通过数据库验证登录的方式中不需要配置此种密码加密方式, 因为已经在JWT配置中指定
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}

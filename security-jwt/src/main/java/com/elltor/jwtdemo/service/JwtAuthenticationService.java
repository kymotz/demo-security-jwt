package com.elltor.jwtdemo.service;

import com.elltor.jwtdemo.conf.security.JwtTokenHelper;
import com.elltor.jwtdemo.domain.LoginUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtAuthenticationService {

    // 此处注入的bean在SpringConfig中产生, 如果不在其中声明则注入AuthenticationManager报错
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenHelper jwtTokenHelper;

    /**
     * 登录认证换取JWT令牌
     */
    public String doAuth4GenerateToken(String username, String password) {
        //用户验证
        Authentication authentication = null;
        try {
            // 进行身份验证,
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new RuntimeException("用户名密码错误");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return jwtTokenHelper.generateToken(loginUser);

    }

}

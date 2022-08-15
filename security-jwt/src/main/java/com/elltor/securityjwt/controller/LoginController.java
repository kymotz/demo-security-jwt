package com.elltor.securityjwt.controller;

import com.elltor.securityjwt.service.JwtAuthenticationService;
import com.elltor.securityjwt.util.RestResult;
import com.elltor.securityjwt.domain.LoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class LoginController {

    @Resource
    private JwtAuthenticationService jwtAuthenticationService;

    // 这个方法用于在登录后登录验证后返回token
    @PostMapping("/login")
    public RestResult login(@RequestBody LoginDTO login) {
        System.out.println("username = " + login.getUsername());
        System.out.println("password = " + login.getPassword());
        RestResult result = RestResult.newInstance();
        result.setCode(200);
        // 该方法会调用UserDetailsServiceImpl的LoadUserByUsername
        String token = jwtAuthenticationService.login(login.getUsername(), login.getPassword());
        result.put("token", token);
        return result;
    }

}

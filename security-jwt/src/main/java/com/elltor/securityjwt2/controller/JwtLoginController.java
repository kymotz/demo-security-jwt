package com.elltor.securityjwt2.controller;

import com.elltor.securityjwt2.service.JwtAuthService;
import com.elltor.securityjwt2.util.RestResult;
import com.elltor.securityjwt2.domain.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JwtLoginController {

    @Autowired
    private JwtAuthService jwtAuthService;

    // 这个方法用于在登录后登录验证后返回token
    @PostMapping("/login")
    public RestResult login(@RequestBody LoginDTO login){
        System.out.println("username = " + login.getUsername());
        System.out.println("password = " + login.getPassword());
        RestResult result = RestResult.newInstance();
        result.setCode(200);
        // 该方法会调用UserDetailsServiceImpl的LoadUserByUsername
        String token = jwtAuthService.login(login.getUsername(), login.getPassword());
        result.put("token",token);
        return result;
    }


}

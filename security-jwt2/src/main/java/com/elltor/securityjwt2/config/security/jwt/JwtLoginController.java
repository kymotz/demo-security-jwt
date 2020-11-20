package com.elltor.securityjwt2.config.security.jwt;

import com.elltor.securityjwt2.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JwtLoginController {

    @Autowired
    private JwtAuthService jwtAuthService;

    // 这个方法用于在登录后登录验证后返回token
    @PostMapping("/login")
    public RestResult login(String username, String password){

        RestResult result = RestResult.newInstance();
        result.setCode(200);
        // 该方法会调用UserDetailsServiceImpl的LoadUserByUsername
        String token = jwtAuthService.login(username,password);
        result.put("token",token);
        return result;
    }


}

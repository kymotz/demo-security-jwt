package com.elltor.securityjwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class SecurityJwt2ApplicationTests {

    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {

        String rawPass = "123456";
        String pass = "$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.";
        boolean matches = passwordEncoder.matches(rawPass, pass);
        System.out.println("matches = " + matches);
    }

}

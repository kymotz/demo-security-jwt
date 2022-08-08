package com.elltor.security.ba;

import com.elltor.security.ba.repo.UserRepository;
import com.elltor.security.ba.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import javax.annotation.Resource;

@SpringBootTest
class SecurityBaApplicationTests {

    @Resource
    UserRepository userRepository;

    @Test
    void contextLoads() {
        User admin = userRepository.findByUsername("admin");
        System.out.println(admin);
    }

    @Test
    void jpaTest() {
        User userExample = new User();
        userExample.setUsername("admin");
        User user = userRepository.findOne(Example.of(userExample)).orElse(null);
        System.out.println(user);
    }

}

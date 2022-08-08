package com.elltor.security.ba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liuqichun
 */
@SpringBootApplication
public class SecurityBaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SecurityBaApplication.class, args);
        // run.getBean(DefaultSecurityFilter.class);
        System.out.println("hello");
    }

}

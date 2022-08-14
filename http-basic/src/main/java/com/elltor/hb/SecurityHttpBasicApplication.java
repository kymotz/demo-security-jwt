package com.elltor.hb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecurityHttpBasicApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SecurityHttpBasicApplication.class,args);
        System.out.println( "Hello World!" );
    }

    @GetMapping(value = "/hello")
    private String helloWorld() {
        return "hello world";
    }

    @PostMapping(value = "/hello-post")
    private String helloWorldByPost() {
        return "hello world. POST";
    }

}

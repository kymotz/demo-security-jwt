package com.elltor.jwtdemo.domain;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}

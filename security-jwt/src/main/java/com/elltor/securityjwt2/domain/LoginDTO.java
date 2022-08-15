package com.elltor.securityjwt2.domain;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}

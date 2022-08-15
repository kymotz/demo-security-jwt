package com.elltor.securityjwt2.domain;

import lombok.Data;

/**
 * @author <a href="kim:username?username=liuqichun03">刘启春(liuqichun03)</a>
 * @since 2022/8/14
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}

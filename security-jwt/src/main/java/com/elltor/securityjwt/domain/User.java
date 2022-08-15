package com.elltor.securityjwt.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(
        name = "user",
        indexes = {
                @Index(name = "password_idx", columnList = "password"),
                @Index(name = "username_idx_unique", columnList = "username", unique = true)
        }
)
public class User implements Serializable {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户账号
     */
    @Column(name = "username", columnDefinition = "varchar(255) not null default '' comment '用户账号'")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", columnDefinition = "varchar(255) not null default '' comment '密码'")
    private String password;

    /**
     * 帐号状态（0正常  1停用）
     */
    @Column(name = "status", columnDefinition = "tinyint(3) not null default '0' comment '帐号状态（0正常  1停用）'")
    private Integer status;

    /**
     * 用户角色（多角色用英文半角逗号","间隔）
     */
    @Column(name = "roles", columnDefinition = "varchar(1024) not null default '' comment '用户角色（多角色用英文半角逗号(,)间隔）'")
    private String roles;

    // jpa 相关都导入 javax.persistence.包下的
}

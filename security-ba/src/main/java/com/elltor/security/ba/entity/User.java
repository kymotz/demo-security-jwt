package com.elltor.security.ba.entity;

import lombok.Data;

// jpa 相关都导入 javax.persistence.包下的
import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户对象  users
 */
@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户账号
     */
    @Column(name = "username", length = 255, unique = true)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", length = 255)
    private String password;

    /**
     * 帐号状态（0正常  1停用）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 用户角色（多角色用英文半角逗号","间隔）
     */
    @Column(name = "roles")
    private String roles;

}

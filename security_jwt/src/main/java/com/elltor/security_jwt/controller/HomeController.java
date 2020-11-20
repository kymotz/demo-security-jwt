package com.elltor.security_jwt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 登录成功首页
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    //用户管理
    @GetMapping("/system/user")
    public String userList(){
        return "user";
    }

    //角色管理
    @GetMapping("/system/role")
    public String roleList(){
        return "role";
    }

    //菜单管理
    @GetMapping("/system/menu")
    public String menuList(){
        return "menu";
    }

    //订单管理
    @GetMapping("/order")
    public String orderList(){
        return "order";
    }




}

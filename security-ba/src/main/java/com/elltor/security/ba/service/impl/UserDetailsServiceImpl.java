package com.elltor.security.ba.service.impl;

import com.elltor.security.ba.entity.LoginUser;
import com.elltor.security.ba.entity.User;

import com.elltor.security.ba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        User user = userService.queryByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }
        System.out.println("user = " + user);
        //将数据库的roles解析为UserDetails的权限集
        //AuthorityUtils.commaSeparatedStringToAuthorityList将逗号分隔的字符集转成权限对象列表
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        String username = user.getUsername();
        if (username == null) {
            throw new IllegalArgumentException("username 为 null");
        }
        LoginUser loginUser = new LoginUser(userService.updatePassword(username, newPassword));
        return loginUser;
    }
}




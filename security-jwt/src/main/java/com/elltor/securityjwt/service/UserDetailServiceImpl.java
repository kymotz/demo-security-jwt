package com.elltor.securityjwt.service;

import com.elltor.securityjwt.domain.LoginUser;
import com.elltor.securityjwt.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        User user = userService.selectUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }
        LoginUser loginUser = new LoginUser(user);
        //将数据库的roles解析为UserDetails的权限集
        //AuthorityUtils.commaSeparatedStringToAuthorityList将逗号分隔的字符集转成权限对象列表
        return loginUser;
    }

}




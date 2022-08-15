package com.elltor.securityjwt2.service;

import com.elltor.securityjwt2.domain.UserDao;
import com.elltor.securityjwt2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User selectUserByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        List<User> list = userDao.findAll(Example.of(user));
        return list.isEmpty() ? null : list.get(0);
    }
}

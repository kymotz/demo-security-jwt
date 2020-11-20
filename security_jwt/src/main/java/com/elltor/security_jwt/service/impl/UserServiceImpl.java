package com.elltor.security_jwt.service.impl;

import com.elltor.security_jwt.dao.UserDao;
import com.elltor.security_jwt.entity.Users;
import com.elltor.security_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Users selectUserByUserName(String userName) {
        Users user = new Users();
        user.setUserName(userName);
        List<Users> list = userDao.findAll(Example.of(user));
        return list.isEmpty() ? null : list.get(0);
    }
}

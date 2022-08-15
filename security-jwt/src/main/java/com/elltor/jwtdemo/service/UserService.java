package com.elltor.jwtdemo.service;

import com.elltor.jwtdemo.repository.UserRepository;
import com.elltor.jwtdemo.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User selectUserByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        List<User> list = userRepository.findAll(Example.of(user));
        return list.isEmpty() ? null : list.get(0);
    }
}

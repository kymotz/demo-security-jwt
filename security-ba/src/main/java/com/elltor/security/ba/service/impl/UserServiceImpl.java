package com.elltor.security.ba.service.impl;

import com.elltor.security.ba.entity.User;
import com.elltor.security.ba.repo.UserRepository;
import com.elltor.security.ba.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User queryByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userRepository.findOne(Example.of(user)).orElse(null);
    }

    @Override
    public User updatePassword(String username, String newEncodedPassword) {
        User user = new User();
        user.setUsername(username);
        User findUser = userRepository.findOne(Example.of(user)).orElseThrow(IllegalArgumentException::new);
        findUser.setPassword(newEncodedPassword);
        userRepository.save(findUser);
        return findUser;
    }
}

package com.elltor.securityjwt.service;

import com.elltor.securityjwt.repository.UserRepository;
import com.elltor.securityjwt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User selectUserByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        List<User> list = userRepository.findAll(Example.of(user));
        return list.isEmpty() ? null : list.get(0);
    }
}

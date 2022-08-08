package com.elltor.security.ba.service;

import com.elltor.security.ba.entity.User;

public  interface UserService {
    public User queryByUsername(String  userName);

    User updatePassword(String username, String newEncodedPassword);
}

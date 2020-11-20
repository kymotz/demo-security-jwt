package com.elltor.security_jwt.service;

import com.elltor.security_jwt.entity.Users;

public  interface  UserService  {
    public Users selectUserByUserName(String  userName);
}

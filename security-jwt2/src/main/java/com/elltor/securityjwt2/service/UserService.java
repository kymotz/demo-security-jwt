package com.elltor.securityjwt2.service;

import com.elltor.securityjwt2.entity.Users;

public  interface  UserService  {
    public Users selectUserByUserName(String userName);
}

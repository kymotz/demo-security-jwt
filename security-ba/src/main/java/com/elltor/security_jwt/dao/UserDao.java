package com.elltor.security_jwt.dao;

import com.elltor.security_jwt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<Users,Long> {


}

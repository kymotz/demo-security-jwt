package com.elltor.securityjwt2.dao;

import com.elltor.securityjwt2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<Users,Long> {

}

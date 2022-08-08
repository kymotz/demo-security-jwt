package com.elltor.security.ba.repo;

import com.elltor.security.ba.entity.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Long> {

    User findByUsername(String username);

}

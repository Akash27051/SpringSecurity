package com.learningSpringSecurity.skySecurity.repository;

import com.learningSpringSecurity.skySecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String users);


}

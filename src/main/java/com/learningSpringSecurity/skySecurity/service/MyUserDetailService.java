package com.learningSpringSecurity.skySecurity.service;

import com.learningSpringSecurity.skySecurity.model.UserPrincipal;
import com.learningSpringSecurity.skySecurity.model.Users;
import com.learningSpringSecurity.skySecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repository.findByUsername(username);

        if (user==null){
            System.out.println("User not found...o/p on console");
            throw new UsernameNotFoundException("User not found... Exeption message");

        }

       return new UserPrincipal(user);

    }
}

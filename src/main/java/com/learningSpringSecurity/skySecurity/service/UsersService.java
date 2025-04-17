package com.learningSpringSecurity.skySecurity.service;

import com.learningSpringSecurity.skySecurity.model.Users;
import com.learningSpringSecurity.skySecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users registerUser(Users users){
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
        users.setPassword(encoder.encode(users.getPassword()));

        Users saved = userRepository.save(users);
        return saved;
    }

    public String verify(Users users) {

        Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

        if(authenticate.isAuthenticated()){

            return jwtService.generateToken(users.getUsername());

        }
        return "fail";


    }
}

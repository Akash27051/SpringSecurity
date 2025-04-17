package com.learningSpringSecurity.skySecurity.controller;

import com.learningSpringSecurity.skySecurity.model.Users;
import com.learningSpringSecurity.skySecurity.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }



    @PostMapping("/register")
    public Users addNewUser(@RequestBody Users users){
        Users savedUser = usersService.registerUser(users);

        return savedUser;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){
        System.out.println(users);

        return usersService.verify(users);
    }


}

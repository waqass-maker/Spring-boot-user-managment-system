package com.springboot.mrspringboot.controller;

import com.springboot.mrspringboot.DataStore.User;
import com.springboot.mrspringboot.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Tag(name=" login user ")
public class publiccontroller {

    @Autowired  
    private UserService userservice;

    @PostMapping
    public void add(@RequestBody User user) {
        userservice.adduser(user);
    }
}

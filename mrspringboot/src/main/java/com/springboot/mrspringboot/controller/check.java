package com.springboot.mrspringboot.controller;

import com.springboot.mrspringboot.DataStore.User;
import com.springboot.mrspringboot.serviceerepo.Querclass;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/check")
@Tag(name="check API")
public class check {
    @Autowired
   public Querclass querclass;

    @GetMapping
    public List<User> query(){
        List<User> getuser = querclass.getuser();
        return getuser;
    }
}

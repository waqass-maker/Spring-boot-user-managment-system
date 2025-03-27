package com.api.prodev.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcehck {
    @GetMapping("health")
    public String get(){
        return "app is good";
    }
}

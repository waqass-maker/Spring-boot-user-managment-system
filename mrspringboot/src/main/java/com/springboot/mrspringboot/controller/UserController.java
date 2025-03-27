package com.springboot.mrspringboot.controller;


import com.springboot.mrspringboot.DataStore.User;
import com.springboot.mrspringboot.service.UserService;
import com.springboot.mrspringboot.service.Weatherservice;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.springboot.mrspringboot.Waqas.weatherApi;


import java.util.List;

@RestController
@RequestMapping("/who")
@Tag(name="user Main API")
public class UserController {  // ✅ Fixed spelling
    @Autowired
    private UserService userservice;
    @Autowired
   private   Weatherservice weatherservice;
    @Autowired
    private weatherApi weatherApi;


    @GetMapping()
    public User byusername(@PathVariable String userName) {
       return userservice.findbyname(userName);
    }

    @PutMapping()
    public User update(@RequestBody User user) {
        User check = userservice.findbyname(user.getUserName());
        if (check != null) {
            check.setUserName(user.getUserName()); // Update username if needed
            check.setPassword(user.getPassword());
            userservice.adduser(check);
        }
        return check;
    }

    @GetMapping("weather")
    public ResponseEntity<?> GREETING(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        weatherApi weatherApi1=weatherservice.getweather("Gujranwala");
        String greeting="";
        if (weatherApi1!=null){
            greeting ="weathef feelis like "+weatherApi1.getCurrent().getFeelslikeF();
        }
        return  new ResponseEntity<>("hi"+authentication.getName()+greeting, HttpStatus.OK);
    }
}

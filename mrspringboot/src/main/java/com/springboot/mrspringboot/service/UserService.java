package com.springboot.mrspringboot.service;


import com.springboot.mrspringboot.DataStore.User;
import com.springboot.mrspringboot.serviceerepo.UserRespo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserService {
//    private static final Logger logger= (Logger) LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRespo userRespo;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRespo userRespo) {
        this.passwordEncoder = passwordEncoder;
        this.userRespo = userRespo;
    }

    public Boolean adduser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRolls(Arrays.asList("USER"));
            userRespo.save(user);
            return true;
        } catch (Exception e) {
            log.info("it is log by waqas for {} ",user.getUserName());
            return false;
        }
    }
//    public void addnew(User user){
//        user.setRolls(Arrays.asList("USER"));
//        userRespo.save(user);
//    }
    public List<User> find() {
        return userRespo.findAll();
    }

    public User findbyname(String name) {
        return userRespo.findByuserName(name);
    }


    public void updateUserEntries(User user) {
        User existingUser = userRespo.findByuserName(user.getUserName());
        existingUser.setGetall(user.getGetall());
        userRespo.save(existingUser);
    }


}

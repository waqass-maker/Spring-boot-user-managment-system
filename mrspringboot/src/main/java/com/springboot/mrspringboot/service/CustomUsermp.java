package com.springboot.mrspringboot.service;

import com.springboot.mrspringboot.DataStore.User;
import com.springboot.mrspringboot.serviceerepo.UserRespo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUsermp  implements UserDetailsService {

    @Autowired
    private UserRespo userRespo;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRespo.findByuserName(userName);
        if (user != null) {
            System.out.println("Loaded user: " + user.getUserName());
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRolls().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found: " + userName);
    }
}

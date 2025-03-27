package com.springboot.mrspringboot.service;

import com.springboot.mrspringboot.DataStore.Pojo;
import com.springboot.mrspringboot.DataStore.User;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.springboot.mrspringboot.serviceerepo.Respo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class springservice {

    private static final Logger logger= (Logger) LoggerFactory.getLogger(springservice.class);
    @Autowired
    private Respo data;

    @Autowired
    private UserService userservice;

    public void saveEntry(Pojo pojo, String userName) {
      try {
          User user = userservice.findbyname(userName);
          Pojo po = data.save(pojo);
          user.getGetall().add(po);
          userservice.updateUserEntries(user);
      }
       catch (Exception e){
          logger.info("it is log by waqas ");
       }
    }


    public List<Pojo> find() {
        return data.findAll();
    }
    public Optional<Pojo> id(ObjectId id){
        return data.findById(id);
    }
    public void del(ObjectId id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userservice.findbyname(userName);
            boolean removed = user.getGetall().removeIf(x -> x.getId().equals(id));
            if (removed) {
                data.deleteById(id);
                userservice.adduser(user);
            } else {
                throw new Exception("Entry not found or user does not have permission to delete this entry.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}

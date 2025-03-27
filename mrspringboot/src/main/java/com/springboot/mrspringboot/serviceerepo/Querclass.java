package com.springboot.mrspringboot.serviceerepo;

import com.springboot.mrspringboot.DataStore.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Querclass {


    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getuser(){
        Query in=new Query();
        in.addCriteria(Criteria.where("email").exists(true));
        in.addCriteria(Criteria.where("sentimental").is(true));
        List<User> users = mongoTemplate.find(in, User.class);
        return users;
    }
}

package com.springboot.mrspringboot.serviceerepo;

import com.springboot.mrspringboot.DataStore.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespo extends MongoRepository<User, String> {
    User findByuserName(String userName);

}
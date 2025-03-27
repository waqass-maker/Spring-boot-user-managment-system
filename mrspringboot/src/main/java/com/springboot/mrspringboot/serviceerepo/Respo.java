package com.springboot.mrspringboot.serviceerepo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.springboot.mrspringboot.DataStore.Pojo;
import org.springframework.stereotype.Repository;
@Repository
public interface Respo extends MongoRepository<Pojo, ObjectId> {
}

package com.example.Delivery.repository;

import com.example.Delivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String name);

}

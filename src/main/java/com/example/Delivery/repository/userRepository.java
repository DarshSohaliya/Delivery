package com.example.Delivery.repository;

import com.example.Delivery.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends MongoRepository<User,String> {

    User findByUsername(String username);

}

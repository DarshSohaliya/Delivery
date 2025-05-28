package com.example.Delivery.repository;

import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends MongoRepository<CourierBooking,String> {
    Object findByUser(User user);

}

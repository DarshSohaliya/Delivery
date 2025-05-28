package com.example.Delivery.repository;

import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends MongoRepository<CourierBooking,String> {
    List<CourierBooking> findByUser(User user);

    CourierBooking findByTrackingId(String trackingId);

}

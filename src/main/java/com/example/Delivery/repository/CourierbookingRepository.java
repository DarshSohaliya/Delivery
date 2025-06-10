package com.example.Delivery.repository;

import com.example.Delivery.model.CourierBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourierbookingRepository extends MongoRepository<CourierBooking,String> {
    List<CourierBooking> findByUserId(String id);

    List<CourierBooking> findByDeliveryBoyId(String id);
}

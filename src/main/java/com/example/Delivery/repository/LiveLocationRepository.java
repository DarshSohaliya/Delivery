package com.example.Delivery.repository;

import com.example.Delivery.model.LiveLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LiveLocationRepository extends MongoRepository<LiveLocation,String> {
    LiveLocation findByDeliveryBoyId(String id);
}

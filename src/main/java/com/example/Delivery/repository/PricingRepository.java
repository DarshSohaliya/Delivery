package com.example.Delivery.repository;

import com.example.Delivery.model.Pricing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PricingRepository extends MongoRepository<Pricing,String> {
    Pricing findTopByOrderByLastUpdatedDesc();

}

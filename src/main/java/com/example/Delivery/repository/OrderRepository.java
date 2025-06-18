package com.example.Delivery.repository;

import com.example.Delivery.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Orders,String> {
    List<Orders> findAllByCustomerId(String customerId);
}

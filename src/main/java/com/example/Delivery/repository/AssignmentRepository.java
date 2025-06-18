package com.example.Delivery.repository;

import com.example.Delivery.model.DeliveryAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends MongoRepository<DeliveryAssignment,String> {

}

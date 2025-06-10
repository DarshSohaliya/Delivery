package com.example.Delivery.repository;

import com.example.Delivery.model.DeliveryStatusUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeliveryStatusUpdateRepository extends MongoRepository<DeliveryStatusUpdate,String> {

    List<DeliveryStatusUpdate> findByBooking_IdOrderByUpdatedAtDesc(String bookingId);
}

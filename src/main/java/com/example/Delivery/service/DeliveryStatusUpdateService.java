package com.example.Delivery.service;

import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.DeliveryStatusUpdate;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.CourierbookingRepository;
import com.example.Delivery.repository.DeliveryStatusUpdateRepository;
import com.example.Delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryStatusUpdateService {
   @Autowired
    CourierbookingRepository bookingRepo;
   @Autowired
    UserRepository userRepo;



    public CourierBooking addStatus(String bookingId, String status, String name) throws Exception {
        CourierBooking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new Exception("Booking not found"));
        User updatedBy = userRepo.findByUsername(name);

//        DeliveryStatusUpdate update = new DeliveryStatusUpdate();
//        update.setBooking(booking);
//        update.setUpdatedBy(updatedBy);
//        update.setStatus(status);
        booking.setStatus(status);
       return bookingRepo.save(booking);


    }

//    public List<DeliveryStatusUpdate> getStatusHistory(String bookingId){
//        return statusRepo.findByBooking_IdOrderByUpdatedAtDesc(bookingId);
//    }
}

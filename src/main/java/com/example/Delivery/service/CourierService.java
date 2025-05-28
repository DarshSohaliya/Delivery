package com.example.Delivery.service;

import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.CourierRepository;
import com.example.Delivery.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
public class CourierService {
    @Autowired
    userRepository userRepository;

    @Autowired
    CourierRepository courierRepository;


    public ResponseEntity<?> createBooking(CourierBooking booking, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        if(user == null ){
            return ResponseEntity.badRequest().body("User Not Found");
        }

        booking.setTrackingId(UUID.randomUUID().toString());
        booking.setStatus("PENDING");
        booking.setUser(user);
        return ResponseEntity.ok(courierRepository.save(booking));
    }


    public ResponseEntity<?> getMyBooking(Principal principal) {
         User user = userRepository.findByUsername(principal.getName());
         if(user == null){
            return ResponseEntity.badRequest().body("User Not Found");
        }
         return ResponseEntity.ok(courierRepository.findByUser(user));
    }

    public ResponseEntity<?> getAllBooking() {

        return ResponseEntity.ok(courierRepository.findAll());
    }

    public ResponseEntity<?> assignDeliveryBoy(String bookingId,String deliveryboyId){
         CourierBooking booking = courierRepository.findById(bookingId).orElseThrow();
         User deliveryboy  = userRepository.findByUsername(deliveryboyId);
         if(deliveryboy == null){
             return ResponseEntity.badRequest().body("User Not Found");
         }
         if(!deliveryboy.getRole().equals("DELIVERY")){
             return ResponseEntity.badRequest().body("User is not DeliveryBoy");
         }
         booking.setDeliveryboy(deliveryboy);
         booking.setStatus("ASSIGNED");
        return ResponseEntity.ok(courierRepository.save(booking));
    }
}

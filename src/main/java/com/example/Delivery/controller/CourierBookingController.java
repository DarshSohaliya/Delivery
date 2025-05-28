package com.example.Delivery.controller;

import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.CourierRepository;
import com.example.Delivery.repository.userRepository;
import com.example.Delivery.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class CourierBookingController {

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private CourierService courierService;

    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createBooking(@RequestBody CourierBooking booking , Principal principal){
       return courierService.createBooking(booking,principal);

    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBooking(){
        return courierService.getAllBooking();
    }


    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getMyBooking(Principal principal){
         return courierService.getMyBooking(principal);
    }

    @PutMapping("/assign/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignCourier(@PathVariable("id") String bookingId,@RequestParam String deliveryboyId ){
        return courierService.assignDeliveryBoy(bookingId,deliveryboyId);
    }

    @PutMapping("update-location/{bookingId}")
    @PreAuthorize("hasRole('DELIVERY')")
    public ResponseEntity<?> updateLocation(@PathVariable("bookingId") String bookingId,@RequestParam Double lat, @RequestParam Double lng ,Principal principal){
       return courierService.updateLoaction(bookingId,lat,lng,principal);
    }

    @PutMapping("/status/{bookingId}")
    @PreAuthorize("hasRole('DELIVERY')")
    public ResponseEntity<?> updateStatus(@PathVariable("bookingId") String bookingId,@RequestParam String status,Principal principal){
        System.out.println(bookingId + " " + status);
        return courierService.updateStatus(bookingId,status,principal);

    }

    @GetMapping("/track/{trackingId}")
    public  ResponseEntity<?> trackParcel(@PathVariable("trackingId") String trackingId){
         return  courierService.trackParcel(trackingId);
    }

}

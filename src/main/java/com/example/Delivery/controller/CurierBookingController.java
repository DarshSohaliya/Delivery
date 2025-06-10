package com.example.Delivery.controller;

import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.service.CourierBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Executable;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class CurierBookingController {

    @Autowired
    private CourierBookingService bookingService;

     @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestParam String pickupAddressId,
                                           @RequestParam String dropAddressId,
                                           @RequestParam String itemDescription,
                                           @RequestParam double weightInKg,
                                           Principal principal
                                           ){
         try{
             CourierBooking booking = bookingService.createBooking(pickupAddressId,dropAddressId,itemDescription,weightInKg,principal);
             return  ResponseEntity.ok(booking);
         }
         catch (Exception e){
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }

     @PreAuthorize(("hasRole('CUSTOMER')"))
     @GetMapping("/my")
    public ResponseEntity<?> getMyBookings(Principal principal){
         try{
             List<CourierBooking> bookings = bookingService.getUserBookings(principal);
             return ResponseEntity.ok(bookings);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }

     @PreAuthorize("hasRole('DELIVERY_BOY')")
     @GetMapping("/assigned")
    public ResponseEntity<?> getAssignedBookings(Principal principal){
         try {
             List<CourierBooking> bookings = bookingService.getbookingAssignedToDeliveryBoy(principal);
            return ResponseEntity.ok(bookings);
         }
         catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
         }
     }

     @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/assign")
    public ResponseEntity<?> assignDeliveryBoy(@RequestParam String bookingId,@RequestParam String deliveryBoyId){
         try{
             bookingService.assignDeliveryBoy(bookingId,deliveryBoyId);
             return ResponseEntity.ok("Delivery boy assigned successfully");
         }
         catch (Exception e){
             return ResponseEntity.badRequest().body(e.getMessage());

         }
     }


}

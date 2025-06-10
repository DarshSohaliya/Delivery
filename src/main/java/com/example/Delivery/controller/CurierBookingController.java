package com.example.Delivery.controller;

import com.example.Delivery.dto.AddressDTO;
import com.example.Delivery.dto.BookingRequestDTO;
import com.example.Delivery.dto.CourierBookingResponseDTO;
import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.service.CourierBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Executable;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class CurierBookingController {

    @Autowired
    private CourierBookingService bookingService;

     @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request,
                                           Principal principal
                                           ){
         try{
             CourierBooking booking = bookingService.createBooking(request.getPickupAddress(), request.getDropAddress(),request.getItemDescription(),request.getWeightInKg(),principal);
             CourierBookingResponseDTO response = bookingService.mapToDTO(booking);
             return  ResponseEntity.ok(response);
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
//             CourierBookingResponseDTO response = bookingService.mapToDTO(booking);
        List<CourierBookingResponseDTO> response =  bookings.stream().map(bookingService::mapToDTO).collect(Collectors.toList());

             return ResponseEntity.ok(response);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }

     @PreAuthorize("hasRole('DELIVERY_BOY')")
     @GetMapping("/assigned")
    public ResponseEntity<?> getAssignedBookings(Principal principal){
         try {
             List<CourierBooking> bookings = bookingService.getbookingAssignedToDeliveryBoy(principal);
             List<CourierBookingResponseDTO> response = bookings.stream()
                     .map(bookingService::mapToDTO)
                     .collect(Collectors.toList());
            return ResponseEntity.ok(response);
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

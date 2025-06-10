package com.example.Delivery.controller;

import com.example.Delivery.dto.CourierBookingResponseDTO;
import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.DeliveryStatusUpdate;
import com.example.Delivery.service.CourierBookingService;
import com.example.Delivery.service.DeliveryStatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/status")
public class DeliveryStatusUpdateController {
  @Autowired
    private DeliveryStatusUpdateService statusService;

  @Autowired
  private CourierBookingService bookingService;

  @PostMapping("/update")
  @PreAuthorize("hasRole('DELIVERY_BOY')")

  public ResponseEntity<?> updateStatus(@RequestParam String bookingId, @RequestParam String status, Principal principal){

      try{
          CourierBooking update = statusService.addStatus(bookingId,status,principal.getName());
          CourierBookingResponseDTO  booking = bookingService.mapToDTO(update);
          return ResponseEntity.ok(booking);
      }catch (Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }

  }

//  @GetMapping("/history/{bookingId}")
//  @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
//  public  ResponseEntity<?> getStatusHistory(@PathVariable String bookingId){
//      return ResponseEntity.ok(statusService.getStatusHistory(bookingId));
//  }

}

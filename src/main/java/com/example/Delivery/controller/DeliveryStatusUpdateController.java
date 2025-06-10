package com.example.Delivery.controller;

import com.example.Delivery.model.DeliveryStatusUpdate;
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

  @PostMapping("/update")
  @PreAuthorize("hasRole('DELIVERY')")

  public ResponseEntity<?> updateStatus(@RequestParam String bookingId, @RequestParam String status, Principal principal){

      try{
          DeliveryStatusUpdate update = statusService.addStatus(bookingId,status,principal.getName());
          return ResponseEntity.ok(update);
      }catch (Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
      }

  }

  @GetMapping("/history/{bookingId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public  ResponseEntity<?> getStatusHistory(@PathVariable String bookingId){
      return ResponseEntity.ok(statusService.getStatusHistory(bookingId));
  }

}

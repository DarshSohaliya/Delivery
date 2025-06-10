package com.example.Delivery.controller;

import com.example.Delivery.model.LiveLocation;
import com.example.Delivery.service.LiveLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/location")
public class LiveLocationController {

    @Autowired private LiveLocationService locationService;

    @PostMapping("/update")
    @PreAuthorize("hasRole('DELIVERY')")
    public ResponseEntity<?> updateLocation(@RequestParam double lat, @RequestParam double lng, Principal principal){
        try{
            LiveLocation  location = locationService.updateLocation(lat,lng,principal.getName());
            return ResponseEntity.ok(location);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}

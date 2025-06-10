package com.example.Delivery.controller;

import com.example.Delivery.model.Address;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.AddressRepository;
import com.example.Delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

     @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAddress(@RequestBody Address address, Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        address.setUser(user);
        Address saved = addressRepository.save(address);
        return  ResponseEntity.ok(saved);
     }
}

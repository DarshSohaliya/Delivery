package com.example.Delivery.controller;

import com.example.Delivery.dto.AuthRequest;
import com.example.Delivery.model.User;
import com.example.Delivery.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    public AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        System.out.println("Inside login method");
        return authService.login(authRequest);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/admin/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(authService.getAllUsers());
//    }
}

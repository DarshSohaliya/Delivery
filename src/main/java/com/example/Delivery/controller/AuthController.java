package com.example.Delivery.controller;

import com.example.Delivery.dto.AuthRequest;
import com.example.Delivery.model.User;
import com.example.Delivery.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return authService.login(authRequest);
    }

}

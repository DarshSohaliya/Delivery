package com.example.Delivery.service;

import com.example.Delivery.dto.AuthRequest;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.UserRepository;
import com.example.Delivery.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    public ResponseEntity<String> register(User user) {
        if(userRepo.findByUsername(user.getUsername()) != null ){
            return  ResponseEntity.badRequest().body("User already exist!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully!");

    }

    public ResponseEntity<?> login(AuthRequest authRequest) {
       try{
           System.out.println(authRequest.getEmail());
           System.out.println(authRequest.getUsername());
           System.out.println(authRequest.getPassword());


             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
           System.out.println("Hello From Below");
       }
       catch (BadCredentialsException e){
           System.out.println("Heelo from catch");
           return ResponseEntity.status(401).build();
       }
       final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(authRequest.getUsername());
        System.out.println("UserDetail::" + userDetails);

       String token = jwtUtils.generateToken(userDetails);
System.out.println("Token::" + token);
       return ResponseEntity.ok(token);
    }

//    public List<User> getAllUsers() {
////        return userRepo.findAll();
//        List<User> list = ;
//        return list;
//    }
}

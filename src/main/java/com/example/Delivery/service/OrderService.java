package com.example.Delivery.service;

import com.example.Delivery.dto.OrderRequest;
import com.example.Delivery.model.Orders;
import com.example.Delivery.model.Package;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.OrderRepository;
import com.example.Delivery.repository.PackageRepository;
import com.example.Delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private UserRepository userRepository;

    public void createOrder(OrderRequest orderRequest, String customerId) {
        User user = userRepository.findById(customerId).orElseThrow();

        Package  p= new Package();
        p.setCustomer(user);
        p.setDescription(orderRequest.getDescription());
        p.setCurrentLocation("WareHouse");
        p.setStatus("CREATED");
        packageRepository.save(p);


        Orders o = new Orders();
        o.setCreatedAt(LocalDateTime.now());
        o.setCustomer(user);
        o.setDescription(orderRequest.getDescription());
        o.setTitle(orderRequest.getTitle());
        o.setOrderPackage(p);
        orderRepository.save(o);
    }

    public ResponseEntity<?> getOrdersByCustomer(String CustomerId) {
       List<Orders> orders= orderRepository.findAllByCustomerId(CustomerId);
       if(orders.isEmpty()){
           throw new RuntimeException("NO Order FOUND !!");
       }
       return ResponseEntity.ok(orders);
    }
}

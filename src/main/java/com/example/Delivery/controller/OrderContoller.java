package com.example.Delivery.controller;

import com.example.Delivery.dto.OrderRequest;
import com.example.Delivery.model.Orders;
import com.example.Delivery.service.OrderService;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderContoller{
    @Autowired
    private  OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest,@RequestParam  String CustomerId){
          orderService.createOrder(orderRequest,CustomerId);
          return  ResponseEntity.ok("Order Created Suceesfully");
    }

    @GetMapping("/my-order")
    public ResponseEntity<?> getMyOrder(@RequestParam String CustomerId){
        return orderService.getOrdersByCustomer(CustomerId);
    }

}

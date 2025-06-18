package com.example.Delivery.controller;

import com.example.Delivery.model.DeliveryAssignment;
import com.example.Delivery.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController{

    @Autowired
    private AgentService agentService;

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/assignments")
    public ResponseEntity<List<DeliveryAssignment>> getAllassignment(@RequestParam String agentId){
        return  ResponseEntity.ok(agentService.getAllassignmentByAgentId(agentId));

    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/update-status")
    public ResponseEntity<String> UpdateStatus(@RequestParam String assignmentId,@RequestParam  String  newStatus){
        agentService.UpdateStatus(assignmentId,newStatus);
        return ResponseEntity.ok("Status Updated");
    }



}

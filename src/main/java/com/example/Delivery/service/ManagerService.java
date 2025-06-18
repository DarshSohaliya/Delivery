package com.example.Delivery.service;

import com.example.Delivery.dto.BulkAssignmentRequest;
import com.example.Delivery.model.DeliveryAssignment;
import com.example.Delivery.model.Package;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.AssignmentRepository;
import com.example.Delivery.repository.PackageRepository;
import com.example.Delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class ManagerService {
    @Autowired private PackageRepository packageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ExecutorService executorService;
    @Autowired private AssignmentRepository assignmentRepository;

    public void assignPackageInParallel(List<BulkAssignmentRequest> requests) {
       for (BulkAssignmentRequest req :  requests){
           executorService.submit(() -> assignPackage(req.getPackageId(),req.getAgentId()));
       }
    }

    public void assignPackage(String packageId, String agentId) {
        Package p = packageRepository.findById(packageId).orElseThrow();
        User agent = userRepository.findById(agentId).orElseThrow();

        DeliveryAssignment assignment = new DeliveryAssignment();
        assignment.setAgent(agent);
        assignment.setAssignedPackage(p);
        assignment.setAssignedAt(LocalDateTime.now());
        assignment.setStatus("ASSIGNED");
        assignmentRepository.save(assignment);

        p.setStatus("ASSIGNED");
        packageRepository.save(p);
    }
}

package com.example.Delivery.service;

import com.example.Delivery.model.DeliveryAssignment;
import com.example.Delivery.model.Package;
import com.example.Delivery.repository.AssignmentRepository;
import com.example.Delivery.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<DeliveryAssignment> getAllassignmentByAgentId(String agentId) {

              return assignmentRepository.findAll().stream().filter(a -> a.getAgent().getId().equals(agentId)).collect(Collectors.toList());
    }

    public void UpdateStatus(String assignmentId, String newStatus) {
        DeliveryAssignment assignment = assignmentRepository.findById(assignmentId).orElseThrow();
        assignment.setStatus(newStatus);
        assignmentRepository.save(assignment);

        Package p = assignment.getAssignedPackage();
        if(newStatus.equals("DELIVERED")){
            p.setStatus("DELIVERED");
        }else if (newStatus.equals("PICKED_UP")) {
            p.setStatus("IN_TRANSIT");
        }
        packageRepository.save(p);
    }
}

package com.example.Delivery.Scheduler;

import com.example.Delivery.model.DeliveryAssignment;
import com.example.Delivery.model.Package;
import com.example.Delivery.repository.AssignmentRepository;
import com.example.Delivery.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class RealTimeDeliveryUpdate {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ExecutorService executorService;


    @Scheduled(fixedRate = 60000)

     public void simulateDeliveryProgress(){
        System.out.println("Hello From In Side RealWold");
         List<DeliveryAssignment> assignments = assignmentRepository.findAll();

         for(DeliveryAssignment assignment:assignments){
             executorService.submit(() -> {
                 if("ASSIGNED".equals(assignment.getStatus())){
                     assignment.setStatus("PICK_UP");
                     assignmentRepository.save(assignment);

                     Package p = assignment.getAssignedPackage();
                     p.setStatus("IN_TRANSIT");
                     p.setCurrentLocation("On Route");
                     packageRepository.save(p);
                 }
                 else if("PICK_UP".equals(assignment.getStatus())){
                       assignment.setStatus("DELIVERED");
                       assignmentRepository.save(assignment);

                       Package p =assignment.getAssignedPackage();
                       p.setStatus("DELIVERED");
                     p.setCurrentLocation("Delivered to destination");
                     packageRepository.save(p);
                 }
             });
         }
     }

}

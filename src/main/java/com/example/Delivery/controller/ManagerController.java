package com.example.Delivery.controller;

import com.example.Delivery.dto.BulkAssignmentRequest;
import com.example.Delivery.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/assign")
    public ResponseEntity<String> assignPackage(@RequestParam String packageId,@RequestParam String agentId){
        managerService.assignPackage(packageId,agentId);
        return ResponseEntity.ok("Package assigned");
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/assign-bulk")
        public ResponseEntity<String> assignPackagesBulk(@RequestBody List<BulkAssignmentRequest> requests){
              managerService.assignPackageInParallel(requests);
              return ResponseEntity.ok("Bulk assignment triggered");
        }

}

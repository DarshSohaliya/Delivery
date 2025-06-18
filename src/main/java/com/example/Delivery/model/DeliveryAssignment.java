package com.example.Delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document()
public class DeliveryAssignment{
    @Id
    private String id;

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Package getAssignedPackage() {
        return assignedPackage;
    }

    public void setAssignedPackage(Package assignedPackage) {
        this.assignedPackage = assignedPackage;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DBRef
    private User agent;

    @DBRef
    private Package assignedPackage;

    private LocalDateTime assignedAt;
    private String status;

}

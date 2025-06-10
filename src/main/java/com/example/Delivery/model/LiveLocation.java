package com.example.Delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("live_locations")
public class LiveLocation {
    @Id
    private String id;

    public User getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(User deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @DBRef
    private User deliveryBoy;

    private double latitude;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private double longitude;

    private LocalDateTime updatedAt;


}

package com.example.Delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("pricing")

public class Pricing {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    @Id
  private String id;

 private double baseFare;
 private double pricePerKm;
 private double pricePerKg;

    public LocalDateTime getUpdatedAt() {
        return lastUpdated;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.lastUpdated = updatedAt;
    }

    private LocalDateTime lastUpdated;

}

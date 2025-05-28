package com.example.Delivery.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class CourierBooking {
    @Id

    private String id;

    private String trackingId;
    private String pickupAddress;
    private String deliveryAddress;
    private Double parcelWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(Double parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String parcelType;
    private String status;

    private Double currentLatitude;
    private Double currentLongitude;

   @DBRef
    private User user;

    public User getDeliveryboy() {
        return deliveryboy;
    }

    public void setDeliveryboy(User deliveryboy) {
        this.deliveryboy = deliveryboy;
    }

    @DBRef
   private User deliveryboy;
}

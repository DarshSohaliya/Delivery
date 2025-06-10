package com.example.Delivery.dto;

import java.time.LocalDateTime;

public class CourierBookingResponseDTO {

    private String id;
    private String trackingId;

    private UserDTO user;
    private UserDTO deliveryBoy;

    private AddressDTO pickupAddress;
    private AddressDTO dropAddress;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(UserDTO deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public AddressDTO getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(AddressDTO pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public AddressDTO getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(AddressDTO dropAddress) {
        this.dropAddress = dropAddress;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(double weightInKg) {
        this.weightInKg = weightInKg;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    private String itemDescription;
    private double weightInKg;
    private double distanceInKm;
    private double totalPrice;
    private String status;
    private LocalDateTime bookingTime;
    private LocalDateTime deliveryTime;


}

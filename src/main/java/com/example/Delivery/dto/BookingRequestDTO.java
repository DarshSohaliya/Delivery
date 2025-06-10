package com.example.Delivery.dto;

public class BookingRequestDTO {
    private AddressDTO pickupAddress;
    private AddressDTO dropAddress;

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

    private String itemDescription;
    private double weightInKg;
}

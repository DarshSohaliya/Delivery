package com.example.Delivery.service;

import com.example.Delivery.model.Address;
import com.example.Delivery.model.CourierBooking;
import com.example.Delivery.model.Pricing;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.AddressRepository;
import com.example.Delivery.repository.CourierbookingRepository;
import com.example.Delivery.repository.PricingRepository;
import com.example.Delivery.repository.UserRepository;
import org.slf4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CourierBookingService {

    @Autowired
    private CourierbookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private PricingRepository pricingRepo;


    public CourierBooking createBooking(String pickupAddressId, String dropAddressId, String itemDescription, double weightInKg, Principal principal) throws Exception{
        User user =userRepo.findByUsername(principal.getName());
        if(user == null) throw new Exception("User not found");

        Address pickup =  addressRepo.findById(pickupAddressId).orElseThrow(() -> new Exception("Pickup address not found"));

        Address drop = addressRepo.findById(dropAddressId).orElseThrow(() -> new Exception("Drop address not found"));

        double distance = calculateDistance(pickup,drop);

        Pricing pricing = pricingRepo.findTopByOrderByLastUpdatedDesc();
        if(pricing == null){
            throw  new Exception("Pricing not configured");
        }
        double total = pricing.getBaseFare() + (pricing.getPricePerKm() * distance) + (pricing.getPricePerKg() * weightInKg);

        CourierBooking booking = new CourierBooking();
        booking.setTrackingId("TRK-" + UUID.randomUUID().toString().substring(0, 8));
        booking.setUser(user);
        booking.setPickupAddress(pickup);
        booking.setDropAddress(drop);
        booking.setItemDescription(itemDescription);
        booking.setWeightInKg(weightInKg);
        booking.setDistanceInKm(distance);
        booking.setTotalPrice(total);
        booking.setStatus("BOOKED");
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepo.save(booking);
    }


    private double calculateDistance(Address pickup, Address drop) {
        return 55.0;
    }

    public List<CourierBooking> getUserBookings(Principal principal) throws  Exception {
       User user = userRepo.findByUsername(principal.getName());
       if(user == null){
           throw  new Exception("User not found");

       }
        return bookingRepo.findByUserId(user.getId());
    }

    public List<CourierBooking> getbookingAssignedToDeliveryBoy(Principal principal) throws Exception {
      User deliveryBoy = userRepo.findByUsername(principal.getName());
      if(deliveryBoy == null) throw new Exception("Delivery boy not found");
       return bookingRepo.findByDeliveryBoyId(deliveryBoy.getId());

    }

    public void assignDeliveryBoy(String bookingId, String deliveryBoyId) throws Exception {
        CourierBooking booking = bookingRepo.findById(bookingId).orElseThrow(() -> new Exception("booking not found"));

        User deliveryBoy = userRepo.findById(deliveryBoyId).orElseThrow(() -> new Exception("Delivery boy not found"));

        if(!deliveryBoy.getRole().contains("DELIVERY")){
            throw new Exception("User is not delivery boy");
        }

        booking.setDeliveryBoy(deliveryBoy);
        booking.setStatus("ASSIGNED");
        bookingRepo.save(booking);
    }
}

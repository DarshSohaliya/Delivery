package com.example.Delivery.service;

import com.example.Delivery.dto.AddressDTO;
import com.example.Delivery.dto.CourierBookingResponseDTO;
import com.example.Delivery.dto.UserDTO;
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
import org.springframework.web.bind.annotation.RequestBody;

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


    public CourierBooking createBooking(@RequestBody AddressDTO pickup,@RequestBody AddressDTO drop, String itemDescription, double weightInKg, Principal principal) throws Exception{
        User user =userRepo.findByUsername(principal.getName());
        if(user == null) throw new Exception("User not found");

//        Address pickup =  addressRepo.findById(pickupAddressId).orElseThrow(() -> new Exception("Pickup address not found"));
           Address pickupadd = new Address();
           pickupadd.setUser(user);
           pickupadd.setStreet(pickup.getStreet());
           pickupadd.setCity(pickup.getCity());
           pickupadd.setLabel(pickup.getLabel());
           pickupadd.setPostalCode(pickup.getPincode());
           pickupadd.setState(pickup.getState());
           pickupadd.setLatitude(pickup.getLatitude());
           pickupadd.setLongitude(pickup.getLongitude());
           pickupadd = addressRepo.save(pickupadd);

        Address dropadd = new Address();
        dropadd.setUser(user);
        dropadd.setStreet(dropadd.getStreet());
        dropadd.setCity(drop.getCity());
        dropadd.setLabel(drop.getLabel());
        dropadd.setPostalCode(drop.getPincode());
        dropadd.setState(drop.getState());
        dropadd.setLatitude(drop.getLatitude());
        dropadd.setLongitude(drop.getLongitude());
        dropadd = addressRepo.save(dropadd);
//        Address drop = addressRepo.findById(dropAddressId).orElseThrow(() -> new Exception("Drop address not found"));

        double distance = calculateDistance(pickup,drop);

//        Pricing pricing = pricingRepo.findTopByOrderByLastUpdatedDesc();
//        if(pricing == null){
//            throw  new Exception("Pricing not configured");
//        }

//        double total = pricing.getBaseFare() + (pricing.getPricePerKm() * distance) + (pricing.getPricePerKg() * weightInKg);

        double total = 66;


        CourierBooking booking = new CourierBooking();
        booking.setTrackingId("TRK-" + UUID.randomUUID().toString().substring(0, 8));
        booking.setUser(user);
        booking.setPickupAddress(pickupadd);
        booking.setDropAddress(dropadd);
        booking.setItemDescription(itemDescription);
        booking.setWeightInKg(weightInKg);
        booking.setDistanceInKm(distance);
        booking.setTotalPrice(total);
        booking.setStatus("BOOKED");
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepo.save(booking);
    }


    private double calculateDistance(AddressDTO pickup, AddressDTO drop) {
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

    public CourierBookingResponseDTO mapToDTO(CourierBooking booking){
        CourierBookingResponseDTO dto = new CourierBookingResponseDTO();

        dto.setId(booking.getId());
        dto.setTrackingId(booking.getTrackingId());


        dto.setUser(mapUser(booking.getUser()));
        dto.setDeliveryBoy(booking.getDeliveryBoy() != null ? mapUser(booking.getDeliveryBoy()) : null);

        dto.setPickupAddress(mapAddress(booking.getPickupAddress()));
        dto.setDropAddress(mapAddress(booking.getDropAddress()));

        dto.setItemDescription(booking.getItemDescription());
        dto.setWeightInKg(booking.getWeightInKg());
        dto.setDistanceInKm(booking.getDistanceInKm());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setStatus(booking.getStatus());
        dto.setBookingTime(booking.getBookingTime());
        dto.setDeliveryTime(booking.getDeliveryTime());

        return dto;
    }

    private UserDTO mapUser(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
    private AddressDTO mapAddress(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setLabel(address.getLabel());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPincode(address.getPostalCode());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());
        return dto;
    }
}

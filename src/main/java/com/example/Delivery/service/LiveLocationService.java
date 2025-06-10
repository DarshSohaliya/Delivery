package com.example.Delivery.service;

import com.example.Delivery.model.LiveLocation;
import com.example.Delivery.model.User;
import com.example.Delivery.repository.LiveLocationRepository;
import com.example.Delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LiveLocationService {
    @Autowired private LiveLocationRepository locationRepo;
    @Autowired private UserRepository userRepo;

    public LiveLocation updateLocation(double lat, double lng, String name) throws Exception {
       User deliveryBoy = userRepo.findByUsername(name);
       if(deliveryBoy == null) throw  new Exception("Delivery boy not found");

       LiveLocation location = locationRepo.findByDeliveryBoyId(deliveryBoy.getId());
       location.setDeliveryBoy(deliveryBoy);
       location.setLatitude(lat);
       location.setLongitude(lng);
       location.setUpdatedAt(LocalDateTime.now());

       return locationRepo.save(location);

    }
    public LiveLocation getLocationDeliveryBoyId(String deliveryBoyId) throws Exception {
        LiveLocation location =  locationRepo.findByDeliveryBoyId(deliveryBoyId);
         if(location == null){
             throw new Exception("Location Not found");

         }
         return location;
    }

}

package com.example.appointmentsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appointmentsystem.model.Availability;
import com.example.appointmentsystem.repositories.AvailabilityRepo;

@Service
public class AvailabilityService {
    
    @Autowired
    private AvailabilityRepo availabilityRepo;

    public List<Availability> getAllAvailabilities(){
     List<Availability> availablities=availabilityRepo.findAll();
      return availablities;
}
public Availability createAvailableService( Availability availability){
   Availability availibleService= availabilityRepo.save(availability);
    return availibleService;

}
}
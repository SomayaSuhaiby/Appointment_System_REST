package com.example.appointmentsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.appointmentsystem.DTOs.AvailabilityDTO;
import com.example.appointmentsystem.exceptions.AvailableServicesNotFoundException;
import com.example.appointmentsystem.exceptions.ServiceNotFoundException;
import com.example.appointmentsystem.model.Availability;
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.repositories.AvailabilityRepo;
import com.example.appointmentsystem.repositories.ServiceRepository;
import com.example.appointmentsystem.repositories.UserRepository;

@Service
public class AvailabilityService {

  private final AvailabilityRepo availabilityRepo;
  public final UserRepository userRepository;
  public final ServiceRepository serviceRepository;

  public AvailabilityService(AvailabilityRepo availabilityRepo, UserRepository userRepository,
      ServiceRepository serviceRepository) {
    this.availabilityRepo = availabilityRepo;
    this.userRepository = userRepository;
    this.serviceRepository = serviceRepository;
  }

  // get all availability services
  public List<Availability> getAllAvailabilities() {
    List<Availability> availablities = availabilityRepo.findAll();
    if (availablities.isEmpty()) {
      throw new AvailableServicesNotFoundException("There is no available services");
    }
    return availablities;
  }

  // create a available service
  public Availability createAvailableService(AvailabilityDTO dto) {

    ServiceModel service = serviceRepository.findById(dto.getServiceId())
        .orElseThrow(() -> new ServiceNotFoundException("Service not exist"));

    Availability availability = new Availability();
    availability.setService(service);
    availability.setStart_time(dto.getStart_time());
    availability.setEnd_time(dto.getEnd_time());
    return availabilityRepo.save(availability);

  }
}
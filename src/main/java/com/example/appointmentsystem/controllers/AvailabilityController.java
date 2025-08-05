package com.example.appointmentsystem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentsystem.DTOs.AvailabilityDTO;
import com.example.appointmentsystem.model.Availability;
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.ServiceRepository;
import com.example.appointmentsystem.repositories.UserRepository;
import com.example.appointmentsystem.services.AvailabilityService;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    // gitting a list of available services
    @GetMapping("/list")
    public ResponseEntity<List<Availability>> getAvailableServices() {

        List<Availability> availablities = availabilityService.getAllAvailabilities();
        return ResponseEntity.ok(availablities);

    }

    // crearing a new available service
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAvailble(@RequestBody AvailabilityDTO dto) {

        if (dto.getServiceProviderId() == null) {
            return ResponseEntity.badRequest().body("Service Provider ID must not be null");
        }

        if (dto.getServiceId() == null) {
            return ResponseEntity.badRequest().body("Service ID must not be null");
        }

        Optional<User> optionalProvider = userRepository.findById(dto.getServiceProviderId());

        if (!optionalProvider.isPresent()) {
            ResponseEntity.badRequest().body("User not found");
        }

        Optional<ServiceModel> OptionalService = serviceRepository.findById(dto.getServiceId());

        if (!OptionalService.isPresent()) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Service not exist");
        }
        
       User provider = optionalProvider.get();
       ServiceModel service = OptionalService.get();

        Availability availability = new Availability();
        availability.setServiceProvider(provider);
        availability.setService(service);
        availability.setStart_time(dto.getStart_time());
        availability.setEnd_time(dto.getEnd_time());
        Availability saved = availabilityService.createAvailableService(availability);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }

}

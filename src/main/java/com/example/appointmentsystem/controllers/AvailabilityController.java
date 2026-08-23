package com.example.appointmentsystem.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentsystem.DTOs.AvailabilityDTO;
import com.example.appointmentsystem.model.Availability;
import com.example.appointmentsystem.services.AvailabilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

      // crearing a new available service
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Availability> createAvailble(@Valid @RequestBody AvailabilityDTO dto) {

        Availability availability = availabilityService.createAvailableService(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(availability);

    }


    // gitting a list of available services
    @GetMapping("/list")
    public ResponseEntity<List<Availability>> getAvailableServices() {

        List<Availability> availablities = availabilityService.getAllAvailabilities();
        return ResponseEntity.ok(availablities);

    }
}

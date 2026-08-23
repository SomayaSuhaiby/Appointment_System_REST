package com.example.appointmentsystem.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentsystem.DTOs.ServiceDTO;
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.services.ServiceModelService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/services")
public class ServiceController {

    private final ServiceModelService serviceModelService;

    public ServiceController(ServiceModelService serviceModelService) {
        this.serviceModelService = serviceModelService;
    }

    // create a new service
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceModel> createService(@RequestBody ServiceDTO dto) {

        ServiceModel savedService = serviceModelService.createService(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    // Get services for a specific user
    @GetMapping("/{providerId}")
    public ResponseEntity<List<ServiceModel>> getServicesByProvider(@Valid @PathVariable Long providerId) {

        List<ServiceModel> services = serviceModelService.getServicesByProviderId(providerId);
        return ResponseEntity.ok(services);
    }
}

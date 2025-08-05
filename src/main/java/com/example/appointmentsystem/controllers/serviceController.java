package com.example.appointmentsystem.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentsystem.DTOs.ServiceDTO;
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.ServiceRepository;
import com.example.appointmentsystem.repositories.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/services")
public class serviceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;


    //Get services for a specific user
    @GetMapping("/{providerId}")
    public ResponseEntity<?> getServicesByProvider(@PathVariable Long providerId){
        
     List<ServiceModel> services=serviceRepository.findByServiceProvider_Id(providerId);
     if (services.isEmpty()) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is now services for this user");
     }
     return ResponseEntity.ok(services);
    }

    //create a new service
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createService(@RequestBody ServiceDTO dto){
        if(dto.getServiceProviderId()==null){
            return ResponseEntity.badRequest().body("service provider Id is required");
        }
        Optional<User> serviceProvider=userRepository.findById(dto.getServiceProviderId());
        if(serviceProvider.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("service provider is not found");
        }
        User provider=serviceProvider.get();
        
        ServiceModel service=new ServiceModel();
        service.setServiceProvider(provider);
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());

        ServiceModel savedService=serviceRepository.save(service);
         return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }
    
    
    
}

package com.example.appointmentsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.appointmentsystem.DTOs.ServiceDTO;
import com.example.appointmentsystem.exceptions.ServiceNotFoundException;
import com.example.appointmentsystem.exceptions.UserNotFoundException;
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.ServiceRepository;
import com.example.appointmentsystem.repositories.UserRepository;

@Service
public class ServiceModelService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ServiceModelService(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    // Get services for a specific user
    public List<ServiceModel> getServicesByProviderId(Long providerId) {

        List<ServiceModel> services = serviceRepository.findByServiceProvider_Id(providerId);

        if (services.isEmpty())
            throw new ServiceNotFoundException("There is now services for this user");

        return services;
    }

    // create service
    public ServiceModel createService(ServiceDTO dto) {

        User serviceProvider = userRepository.findById(dto.getServiceProviderId())
                .orElseThrow(() -> new UserNotFoundException("service provider is not found"));

        ServiceModel service = new ServiceModel();
        service.setServiceProvider(serviceProvider);
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        return serviceRepository.save(service);

    }

}

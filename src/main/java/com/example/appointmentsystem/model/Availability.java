package com.example.appointmentsystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="availability")
public class Availability {
     @Id
    @GeneratedValue(strategy = (GenerationType.IDENTITY))
    private Long id;

     @ManyToOne
     @JoinColumn(name = "service_provider_id")
     private User serviceProvider;

     @ManyToOne
      @JoinColumn(name = "service_id")
      private ServiceModel service;

     private LocalDateTime start_time;
     private LocalDateTime end_time;



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getServiceProvider() {
        return this.serviceProvider;
    }

    public void setServiceProvider(User serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public ServiceModel getService() {
        return this.service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public LocalDateTime getStart_time() {
        return this.start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

}

/*
 {
    "serviceProvider" :
    {
        "id" :6,
        "email": "owais3@gmail.com",
  "password": "$2a$10$s7FRn2XYIP8d9VfgjaukHeSmUV10RoMEG2fqQs3yzwkkaIAEgVp2.",
    "username": "Owais3",
    "roles": {
        "id":"1",
        "name":"name"
    }
    },
    "start_time":"2025-08-02T10:00:00",
    "end_time":"2025-08-02T11:00:00",
      "service":
    {
      "id": 3,
      "name": "eyes test",
      "description": "entirly eyes test",
      "price":"5000",
    
      "serviceProvider":

    }
}
 */

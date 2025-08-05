package com.example.appointmentsystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private ServiceModel service;

  private LocalDateTime appointment_time;
  @Enumerated(EnumType.STRING) 
  private Status status=Status.pending;
  
  public enum Status{
    pending,confirmed,cancelled;
  }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceModel getService() {
        return this.service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public LocalDateTime getAppointments_time() {
        return this.appointment_time;
    }

    public void setAppointments_time(LocalDateTime appointments_time) {
        this.appointment_time = appointments_time;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
   
}
/*
  "id": 1,
    "user": {
        "id": 1,
        "username": "somaya",
        "email": "somaya@gmil.com",
        "password": "somaya123 ",
        "role": "admin",
        "createdAt": "2025-05-06T19:38:30"
    },
    "service": {
        "id": 2,
        "name": "conducting test",
        "description": "conducting test for patient",
        "price": 5000.00,
        "serviceProvider": {
            "id": 1,
            "username": "somaya",
            "email": "somaya@gmil.com",
            "password": "somaya123 ",
            "role": "admin",
            "createdAt": "2025-05-06T19:38:30"
        }
    },
    "status": "pending",
    "appointments_time": "2025-05-06T19:38:30"
}
 
 */
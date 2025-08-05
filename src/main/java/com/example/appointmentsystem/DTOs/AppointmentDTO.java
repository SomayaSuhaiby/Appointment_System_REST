package com.example.appointmentsystem.DTOs;

import java.time.LocalDateTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AppointmentDTO {
    private Long userId;
    private Long serviceId;
    private LocalDateTime appointment_time;
    @Enumerated(EnumType.STRING) 
  private com.example.appointmentsystem.model.Appointment.Status status;
  
  public enum Status{
    pending,confirmed,cancelled;
  }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getAppointment_time() {
        return this.appointment_time;
    }

    public void setAppointment_time(LocalDateTime appointment_time) {
        this.appointment_time = appointment_time;
    }

    public com.example.appointmentsystem.model.Appointment.Status getStatus() {
        return this.status;
    }

    public void setStatus(com.example.appointmentsystem.model.Appointment.Status status) {
        this.status = status;
    }

}

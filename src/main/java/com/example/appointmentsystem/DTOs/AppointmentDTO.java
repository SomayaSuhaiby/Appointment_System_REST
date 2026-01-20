package com.example.appointmentsystem.DTOs;

import java.time.LocalDateTime;

import com.example.appointmentsystem.model.Appointment;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class AppointmentDTO {

    @NotNull(message = "Service Id is required")
    private Long serviceId;

    @NotNull
    private LocalDateTime appointment_time;

    @Enumerated(EnumType.STRING)
    private Appointment.Status status;

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

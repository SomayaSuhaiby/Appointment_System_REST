package com.example.appointmentsystem.DTOs;

import java.time.LocalDateTime;

public class AvailabilityDTO {
    private Long serviceProviderId;
    private Long serviceId;
    private LocalDateTime start_time;
    private LocalDateTime end_time;

    public Long getServiceProviderId() {
        return this.serviceProviderId;
    }

    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public Long getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
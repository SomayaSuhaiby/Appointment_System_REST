package com.example.appointmentsystem.DTOs;

import java.math.BigDecimal;

public class ServiceDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Long serviceProviderId;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getServiceProviderId() {
        return this.serviceProviderId;
    }

    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    
}

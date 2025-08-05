package com.example.appointmentsystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appointmentsystem.model.Availability;

public interface AvailabilityRepo extends JpaRepository<Availability,Long> {
//List<Availability> findByServiceProvider_Id(Long serviceProviderId);
}

package com.example.appointmentsystem.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appointmentsystem.model.Appointment;

public interface appointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByUser_Id(Long userId);
    List<Appointment> findByServiceId(Long serviceId);
    boolean existsByServiceIdAndAppointmentTime( Long serviceId, LocalDateTime appointmentTime);
}

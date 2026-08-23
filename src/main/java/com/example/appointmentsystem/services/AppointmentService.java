package com.example.appointmentsystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.appointmentsystem.DTOs.AppointmentDTO;
import com.example.appointmentsystem.exceptions.AppointmentAlreadyBookedException;
import com.example.appointmentsystem.exceptions.AppointmentNotAvailableException;
import com.example.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.example.appointmentsystem.exceptions.InvalidAppointmentStatusException;
import com.example.appointmentsystem.exceptions.ServiceNotFoundException;
import com.example.appointmentsystem.exceptions.UserNotFoundException;
import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.model.Availability;
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.AvailabilityRepo;
import com.example.appointmentsystem.repositories.ServiceRepository;
import com.example.appointmentsystem.repositories.UserRepository;
import com.example.appointmentsystem.repositories.appointmentRepository;

@Service
public class AppointmentService {
    private final appointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final AvailabilityRepo availabilityRepo;

    public AppointmentService(appointmentRepository appointmentRepository, UserRepository userRepository,
            ServiceRepository serviceRepository, AvailabilityRepo availabilityRepo) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.availabilityRepo = availabilityRepo;
    }

    // Book Appointment
    public Appointment bookAppointment(AppointmentDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("Authenticated user not found");
        }

        ServiceModel service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new ServiceNotFoundException("Service not exist"));

        List<Availability> availabilities = availabilityRepo.findByServiceId(dto.getServiceId());
        if (availabilities.isEmpty())
            throw new ServiceNotFoundException("Service not available");

        LocalDateTime appointmentTime = dto.getAppointment_time();
        boolean available = availabilities.stream().anyMatch(a -> !appointmentTime.isBefore(a.getStart_time())
                && !appointmentTime.isAfter(a.getEnd_time()));

        if (!available)
            throw new AppointmentNotAvailableException(" The requested time is not available");

        boolean alreadyBooked=appointmentRepository.existsByServiceIdAndAppointmentTime(dto.getServiceId(),appointmentTime);
        if (alreadyBooked)throw new AppointmentAlreadyBookedException("This appointment time is already booked");
            

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setService(service);
        appointment.setAppointments_time(appointmentTime);
        return appointmentRepository.save(appointment);

    }

    // Get all appointment for specific user
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        List<Appointment> appointments = appointmentRepository.findByUser_Id(userId);
        if (appointments.isEmpty())
            throw new AppointmentNotFoundException("There is no appointment for this user");
        return appointments;
    }

    // Get all appointment for specific user
    public List<Appointment> getAppointmentsByServiceId(Long serviceId) {
        List<Appointment> appointments = appointmentRepository.findByServiceId(serviceId);
        if (appointments.isEmpty())
            throw new AppointmentNotFoundException("There is no appointment for this service");
        return appointments;
    }

    // Update Appointment Status
    public Appointment updateAppointmentStatus(String status, Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("This is appointment not found"));
        Appointment.Status newStatus = null;
        try {
            newStatus = Appointment.Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidAppointmentStatusException("Invalid appointment status: " + status);
        }

        appointment.setStatus(newStatus);
        return appointmentRepository.save(appointment);
    }

}

package com.example.appointmentsystem.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentsystem.DTOs.AppointmentDTO;
import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.services.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

  public AppointmentController( AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

     //book a new appointment
     @PostMapping("/book")
     public ResponseEntity<Appointment> bookAppointment( @Valid @RequestBody AppointmentDTO dto){

        Appointment savedAppointment= appointmentService.bookAppointment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
     }
     
     //Get appointments for a specific user
     @GetMapping("/user/{userId}")
     public ResponseEntity<List<Appointment>> getAppointmentsForUser(@PathVariable Long userId){

        List<Appointment> appointments=appointmentService.getAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
     }

      //Get appointments for a specific service 
     @GetMapping("/service/{serviceId}")
     public ResponseEntity<List<Appointment>> getAppointmentsForService(@PathVariable Long serviceId){

     List<Appointment> appointments=appointmentService.getAppointmentsByServiceId(serviceId);
        return ResponseEntity.ok(appointments);
     }

     //update appointment status(confirmed,cancelled)
     @PutMapping("/update/{id}")
     public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable Long id,@RequestParam String status){

         Appointment updatedAppointment =appointmentService.updateAppointmentStatus(status, id);
          return ResponseEntity.ok(updatedAppointment);
     }
    
}





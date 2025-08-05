package com.example.appointmentsystem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.appointmentsystem.model.ServiceModel;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.appointmentRepository;
import com.example.appointmentsystem.repositories.ServiceRepository;
import com.example.appointmentsystem.repositories.UserRepository;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private appointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
     //book a new appointment
     @PostMapping("/book")
     public ResponseEntity<?> bookapointment(@RequestBody AppointmentDTO dto){

        //............user id.............................
        if (dto.getUserId()==null) {
            return ResponseEntity.badRequest().body("user Id is required");
        }
       Optional<User> user=userRepository.findById(dto.getUserId());
       if (user.isEmpty()) {
         return  ResponseEntity.badRequest().body("user  is not found");
       }
    
        //...........service id .................
     if (dto.getServiceId()==null) {
        return ResponseEntity.badRequest().body("service id is required");
     }
      Optional<ServiceModel> service=serviceRepository.findById(dto.getServiceId());
      if (service.isEmpty()) {
        return ResponseEntity.badRequest().body("service  is not found");
      }
       
     //************************************************************************** */
      Appointment appointment=new Appointment();
      appointment.setUser(user.get());
      appointment.setService(service.get());
      appointment.setAppointments_time(dto.getAppointment_time());
      appointment.setStatus(dto.getStatus());

        Appointment savedAppointment= appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
     }
     
     //Get appointments for a specific user
     @GetMapping("/user/{userId}")
     public ResponseEntity<?> getAppointmentForUser(@PathVariable Long userId){
        List<Appointment> appointment=appointmentRepository.findByUser_Id(userId);
        if (appointment.isEmpty()) {
         return ResponseEntity.badRequest().body("there is no appointment for this user");
      }
        return ResponseEntity.ok(appointment);
     }

      //Get appointments for a specific service 
     @GetMapping("/service/{serviceId}")
     public ResponseEntity<?> getAppointmentForProvider(@PathVariable Long serviceId){
     List<Appointment> appointment=appointmentRepository.findByServiceId(serviceId);
      if (appointment.isEmpty()) {
         return ResponseEntity.badRequest().body("there is no appointment for this service");
      }
      
        return ResponseEntity.ok(appointment);
     }
     //update appointment status(confirmed,cancelled)
     @PutMapping("/update/{id}")
     public ResponseEntity<?> updateAppointmentStatus(@PathVariable Long id,@RequestParam String status){
        Appointment appointment=appointmentRepository.findById(id).orElse(null);
        if (appointment!=null) {
          appointment.setStatus(Appointment.Status.valueOf(status));
          appointmentRepository.save(appointment);
          return ResponseEntity.ok(appointment);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("this appointment is not found");
        
     }
    
}





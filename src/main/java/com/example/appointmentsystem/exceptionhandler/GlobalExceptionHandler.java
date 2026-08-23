package com.example.appointmentsystem.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.appointmentsystem.DTOs.ErrorDetails;
import com.example.appointmentsystem.exceptions.AppointmentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
     @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleAppointmentNotFoundException(
            AppointmentNotFoundException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Appointment Not Found"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.NOT_FOUND
        );
    }


     @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Internal Server Error"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

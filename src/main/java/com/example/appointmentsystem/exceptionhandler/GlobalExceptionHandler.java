package com.example.appointmentsystem.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.appointmentsystem.DTOs.ErrorDetails;
import com.example.appointmentsystem.exceptions.AppointmentAlreadyBookedException;
import com.example.appointmentsystem.exceptions.AppointmentNotAvailableException;
import com.example.appointmentsystem.exceptions.AppointmentNotFoundException;
import com.example.appointmentsystem.exceptions.AvailableServicesNotFoundException;
import com.example.appointmentsystem.exceptions.InvalidAppointmentStatusException;
import com.example.appointmentsystem.exceptions.InvalidCredentialsException;
import com.example.appointmentsystem.exceptions.RoleNotFoundException;
import com.example.appointmentsystem.exceptions.ServiceNotAvailableException;
import com.example.appointmentsystem.exceptions.ServiceNotFoundException;
import com.example.appointmentsystem.exceptions.UserAlreadyExistsException;
import com.example.appointmentsystem.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
    // =========================
    // APPOINTMENT EXCEPTIONS
    // =========================

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


    @ExceptionHandler(AppointmentNotAvailableException.class)
    public ResponseEntity<ErrorDetails> handleAppointmentNotAvailableException(
            AppointmentNotAvailableException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Appointment Not Available"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.CONFLICT
        );
    }


    @ExceptionHandler(AppointmentAlreadyBookedException.class)
    public ResponseEntity<ErrorDetails> handleAppointmentAlreadyBookedException(
            AppointmentAlreadyBookedException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Appointment Already Booked"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.CONFLICT
        );
    }


    @ExceptionHandler(InvalidAppointmentStatusException.class)
    public ResponseEntity<ErrorDetails> handleInvalidAppointmentStatusException(
            InvalidAppointmentStatusException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Invalid Appointment Status"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.BAD_REQUEST
        );
    }


    // =========================
    // SERVICE EXCEPTIONS
    // =========================

    @ExceptionHandler(AvailableServicesNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleAvailableServicesNotFoundException(
            AvailableServicesNotFoundException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Available Services Not Found"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(ServiceNotAvailableException.class)
    public ResponseEntity<ErrorDetails> handleServiceNotAvailableException(
            ServiceNotAvailableException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Service Not Available"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleServiceNotFoundException(
            ServiceNotFoundException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Service Not Found"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.NOT_FOUND
        );
    }


    // =========================
    // USER EXCEPTIONS
    // =========================

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(
            UserNotFoundException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "User Not Found"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(
            UserAlreadyExistsException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "User Already Exists"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.CONFLICT
        );
    }


    // =========================
    // AUTHENTICATION EXCEPTIONS
    // =========================

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCredentialsException(
            InvalidCredentialsException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Invalid Credentials"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.UNAUTHORIZED
        );
    }


    // =========================
    // ROLE EXCEPTIONS
    // =========================

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleRoleNotFoundException(
            RoleNotFoundException ex) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Role Not Found"
        );

        return new ResponseEntity<>(
                errorDetails,
                HttpStatus.NOT_FOUND
        );
    }


    // =========================
    // UNEXPECTED EXCEPTIONS
    // =========================

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

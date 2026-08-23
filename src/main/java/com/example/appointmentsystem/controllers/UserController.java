package com.example.appointmentsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.appointmentsystem.DTOs.LoginRequestDTO;
import com.example.appointmentsystem.DTOs.UserDTO;
import com.example.appointmentsystem.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  // Registration
  @PostMapping(value = "/register", consumes = "application/json")
  public ResponseEntity<String> register(@Valid @RequestBody UserDTO dto) {

    userService.register(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfuly");
  }

  // login
  @PostMapping(value = "/login", consumes = "application/json")
  public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO dto) {

    userService.login(dto);
    return ResponseEntity.ok("login successful");
  }
}

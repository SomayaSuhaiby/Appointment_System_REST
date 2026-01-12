package com.example.appointmentsystem.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appointmentsystem.DTOs.LoginRequestDTO;
import com.example.appointmentsystem.DTOs.UserDTO;
import com.example.appointmentsystem.model.Role;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.RoleRepository;
import com.example.appointmentsystem.repositories.UserRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
     @Autowired
    private UserRepository userRepository;

	@Autowired 
     private PasswordEncoder passwordEncoder;

     @Autowired
     private RoleRepository roleRepository;

//Registration 
@PostMapping(value = "/register", consumes = "application/json")
public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto) {

    
	  if (userRepository.findByEmail(dto.getEmail())!= null) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }	
        if (dto.getRoleId()==null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role ID is required");
        }
      
    Optional<Role> role=roleRepository.findById(dto.getRoleId());
    if (role.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role not found");
    }
    Role roleValue=  role.get();
   // Set<Role> getRole=Set.of(roleValue);
        User user=new User();
	 user.setPassword(passwordEncoder.encode(dto.getPassword()));
      user.setUsername(dto.getUsername());
      user.setEmail(dto.getEmail());
      user.setRole(Set.of(roleValue));
    

	
     userRepository.save(user);
     return ResponseEntity.status(HttpStatus.CREATED).body("User created successfuly");
	
}

// login 
@PostMapping(value = "/login", consumes = "application/json")
public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO dto){
 User foundUser=userRepository.findByEmail(dto.getEmail());
 if(foundUser!=null&&passwordEncoder.matches(dto.getPassword(), foundUser.getPassword()))
 return ResponseEntity.ok("login successful");
 else
 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
}     
}


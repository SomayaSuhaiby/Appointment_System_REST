package com.example.appointmentsystem.DTOs;

 
import com.example.appointmentsystem.model.User;

public class LoginResponse {
     private Long id;
    private String email;
    private String password;
    
    public LoginResponse(User user){
        this.id=user.getId();
        this.email=user.getEmail();
        this.password=user.getPassword();
       // this.role=user.getRole().name();//covert from enum to string
    }

}
 


package com.example.appointmentsystem.services;

import java.util.ArrayList;
import java.util.List;

import java.lang.String;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.appointmentsystem.model.Role;
import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.UserRepository;

@Service
public class CustomUserDetailsServices implements UserDetailsService{

   private final UserRepository userRepository;

    public CustomUserDetailsServices(UserRepository userRepository){
      this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         System.out.println("AUTH ATTEMPT FOR******************************: " + email);
         
      User user=userRepository.findByEmail(email);
   
      if (user==null) {
        throw new UsernameNotFoundException("User not found: "+ email);
      }
List<GrantedAuthority> authorities = new ArrayList<>();
for (Role role : user.getRole()) {
    String roleName = "ROLE_" + role.getName().toUpperCase();
    authorities.add(new SimpleGrantedAuthority(roleName));
}

      return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        authorities
      
      );
    }
    
}

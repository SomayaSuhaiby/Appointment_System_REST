package com.example.appointmentsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.appointmentsystem.model.Role;



public interface RoleRepository extends JpaRepository<Role,Long>{
Optional<Role> findByName(String name);
}

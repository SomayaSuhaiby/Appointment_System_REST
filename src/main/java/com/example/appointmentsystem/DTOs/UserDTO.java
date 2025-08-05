package com.example.appointmentsystem.DTOs;

import java.time.LocalDateTime;


public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Long roleId;
    private LocalDateTime  createdAt=LocalDateTime.now();

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roles) {
        this.roleId = roles;
    }

}

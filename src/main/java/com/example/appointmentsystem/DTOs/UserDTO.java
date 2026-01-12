package com.example.appointmentsystem.DTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message = "User name is required")
    @Size(min = 3)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 charac")
    private String password;

    @Email(message = "Invalid email adress")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Rol id is required")
    private Long roleId;





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

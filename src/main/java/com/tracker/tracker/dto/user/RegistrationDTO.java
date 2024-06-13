package com.tracker.tracker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegistrationDTO {

    @NotEmpty(message = "username can't be empty")
    @Size(min = 2, max = 50, message = "username should be between 2 and 50 symbols")
    private String username;

    @NotEmpty(message = "email can't be empty")
    @Email
    private String email;

    @NotEmpty(message = "password can't be empty")
    @Size(min = 8, message = "Password should be greater than 8 symbols")
    private String password;

    public String getUsername() {
        return username;
    }

    public RegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}

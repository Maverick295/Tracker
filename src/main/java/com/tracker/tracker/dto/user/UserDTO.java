package com.tracker.tracker.dto.user;

import com.tracker.tracker.patterns.Patterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserDTO {

    @NotEmpty(message = "email can't be empty")
    @Email
    private String email;

    @Pattern(regexp = Patterns.PHONE, message = "The number doesn't match the format")
    @NotEmpty(message = "number can't be empty")
    private String phone;

    @NotEmpty(message = "username can't be empty")
    @Size(min = 2, max = 50, message = "username should be between 2 and 50 symbols")
    private String username;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 2, max = 50, message = "name should be between 2 and 50 symbols")
    private String name;

    @NotEmpty(message = "surname can't be empty")
    @Size(min = 2, max = 50, message = "surname should be between 2 and 50 symbols")
    private String surname;
    private LocalDate dateOfCreate;

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }

    public UserDTO setDateOfCreate(LocalDate dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
        return this;
    }
}

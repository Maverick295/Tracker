package com.tracker.tracker.dto.user;

import java.time.LocalDate;

public class UserDTO {
    private String email;
    private String phone;
    private String username;
    private String name;
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

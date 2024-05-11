package com.tracker.tracker.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String uuid;
    private String email;
    private String phone;
    private String password;
    private String username;
    private String name;
    private String surname;
    private boolean active;
    private String role;
    private LocalDateTime dateOfCreate;

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getUuid() {
        return uuid;
    }

    public Customer setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Customer setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Customer setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Customer setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Customer setRole(String role) {
        this.role = role;
        return this;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public Customer setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
        return this;
    }
}

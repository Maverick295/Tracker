package com.tracker.tracker.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "customers")
@Data
public class Customer implements Serializable {
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
    private static final long serialVersionUID = 1L;

    @Column(name = "role")
    private String roles;
    private LocalDateTime dateOfCreate;

    // Getters and setters

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

    public String getRoles() {
        return roles;
    }

    public Customer setRoles(String roles) {
        this.roles = roles;
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

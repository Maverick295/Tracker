package com.tracker.tracker.models.profile;

public class ProfileModel {
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phone;

    public String getUsername() {
        return username;
    }

    public ProfileModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfileModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public ProfileModel setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ProfileModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}

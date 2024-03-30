package com.tracker.tracker.form;

public class SignUpForm {
    private String username;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public SignUpForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SignUpForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpForm setPassword(String password) {
        this.password = password;
        return this;
    }
}

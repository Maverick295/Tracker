package com.tracker.tracker.forms.profile;

public class AccountInfoChangeForm {
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public AccountInfoChangeForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountInfoChangeForm setEmail(String email) {
        this.email = email;
        return this;
    }
}

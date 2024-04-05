package com.tracker.tracker.forms.profile;

public class PersonalInfoChangeForm {
    private String name;
    private String surname;
    private String phone;

    public String getName() {
        return name;
    }

    public PersonalInfoChangeForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public PersonalInfoChangeForm setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PersonalInfoChangeForm setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}

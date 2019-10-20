package com.example.appdraweractivitytest;

public class UserInformation {
    String email;
    String contact;
    String name;

    public UserInformation() {

    }

    public UserInformation(String email, String contact, String name) {
        this.email = email;
        this.contact = contact;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }
}

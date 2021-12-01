package com.example.carwash;

public class User {
    String Username, Password, Firstname, Lastname;
    public User() {
}

    public User(String username, String password, String firstname, String lastname) {
        this.Username = username;
        this.Password = password;
        this.Firstname = firstname;
        this.Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        this.Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        this.Lastname = lastname;
    }
}

package com.example.bookmoviestickets.Activities;

public class User {
    public String username, password, email, phone, date;
    public User() {
        // Constructor trống cho Firebase
    }
    public User(String username, String password, String email, String phone, String date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


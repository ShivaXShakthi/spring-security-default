package com.example.demo.bindings;

import com.example.demo.entity.Users;

import java.time.Instant;

public class RefreshToken {

    private String token;
    private Instant expiryDate;

    public RefreshToken() {
    }

    public RefreshToken(String token, Instant expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

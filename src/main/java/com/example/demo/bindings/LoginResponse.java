package com.example.demo.bindings;

import java.util.List;

public class LoginResponse {

    private String username;
    private String token;
    private List<String> roles;
    private RefreshToken refreshToken;


    public LoginResponse(String token, List<String> roles, String username, RefreshToken refreshToken) {
        this.token = token;
        this.roles = roles;
        this.username = username;
        this.refreshToken = refreshToken;
    }

    public LoginResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }
}

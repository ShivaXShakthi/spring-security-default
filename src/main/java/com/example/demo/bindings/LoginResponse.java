package com.example.demo.bindings;

import java.util.List;

public class LoginResponse {

    private String username;
    private String token;
    private List<String> roles;


    public LoginResponse(String token, List<String> roles, String username) {
        this.token = token;
        this.roles = roles;
        this.username = username;
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
}

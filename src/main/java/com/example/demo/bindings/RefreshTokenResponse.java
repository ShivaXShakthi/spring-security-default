package com.example.demo.bindings;

public class RefreshTokenResponse {
    private String refreshtoken;

    public RefreshTokenResponse(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}

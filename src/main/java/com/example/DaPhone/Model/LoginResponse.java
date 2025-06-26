package com.example.DaPhone.Model;

public class LoginResponse {
    private String auth;
    private String username;
    private Long userId;

    // Constructor không tham số
    public LoginResponse() {
    }

    // Constructor 2 tham số
    public LoginResponse(String auth, String username) {
        this.auth = auth;
        this.username = username;
    }

    // Constructor đầy đủ 3 tham số
    public LoginResponse(String auth, String username, Long userId) {
        this.auth = auth;
        this.username = username;
        this.userId = userId;
    }

    // Getter và Setter
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

package com.imadelfetouh.adminservice.model.jwt;

public class UserData {

    private String userId;
    private String username;
    private String role;

    public UserData() {

    }

    public UserData(String userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

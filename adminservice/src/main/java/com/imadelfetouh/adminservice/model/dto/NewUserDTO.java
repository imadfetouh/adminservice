package com.imadelfetouh.adminservice.model.dto;

import java.io.Serializable;

public class NewUserDTO implements Serializable {

    private String userId;
    private String username;
    private String password;
    private String role;
    private String photo;
    private ProfileDTO profile;

    public NewUserDTO(String userId, String username, String password, String role, String photo, ProfileDTO profile) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.photo = photo;
        this.profile = profile;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public ProfileDTO getProfile() {
        return profile;
    }
}

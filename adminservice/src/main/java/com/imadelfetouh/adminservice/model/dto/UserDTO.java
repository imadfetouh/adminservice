package com.imadelfetouh.adminservice.model.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String userId;
    private String role;
    private String username;
    private String photo;
    private String bio;
    private String location;
    private String website;

    public UserDTO() {

    }

    public UserDTO(String userId, String role, String username, String photo, String bio, String location, String website) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.photo = photo;
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }
}

package com.imadelfetouh.adminservice.model.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {

    private String profileId;
    private String bio;
    private String location;
    private String website;

    public ProfileDTO(String profileId, String bio, String location, String website) {
        this.profileId = profileId;
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
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
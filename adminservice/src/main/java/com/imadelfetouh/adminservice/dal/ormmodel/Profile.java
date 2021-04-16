package com.imadelfetouh.adminservice.dal.ormmodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "profile")
public class Profile implements Serializable {

    public Profile() {

    }

    public Profile(String profileId, String bio, String location, String website) {
        this.profileId = profileId;
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "profileId")
    private String profileId;

    @Column(name = "bio")
    private String bio;

    @Column(name = "location")
    private String location;

    @Column(name = "website")
    private String website;

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }
}

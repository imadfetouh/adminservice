package com.imadelfetouh.adminservice.model.dto;

import java.io.Serializable;

public class NewUserDTO implements Serializable {

    private String username;
    private String password;

    public NewUserDTO() {

    }

    public NewUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
}

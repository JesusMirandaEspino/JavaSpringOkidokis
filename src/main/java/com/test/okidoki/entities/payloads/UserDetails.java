package com.test.okidoki.entities.payloads;

import com.test.okidoki.entities.User;

public class UserDetails {

    private Boolean isExist;

    private User user;

    private String token;

    public UserDetails() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

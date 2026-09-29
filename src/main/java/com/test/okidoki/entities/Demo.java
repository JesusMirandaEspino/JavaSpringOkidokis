package com.test.okidoki.entities;

import com.test.okidoki.entities.payloads.Roles;

import java.util.List;

public class Demo {

    private String p;

    private String username;

    private List<Roles> roles;

    private String password;

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Demo() {
    }

    public Demo(String p, String username, List<Roles> roles, String password) {
        this.p = p;
        this.username = username;
        this.roles = roles;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

}

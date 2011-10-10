package com.suntek.common.persistence;

import java.util.List;

public class User {

    public String userid;
    public String password;
    public String firstname;
    public String lastname;
    public String description;
    public List<String> roles;

    public User(String userid, String password, String firstname, String lastname, String description) {
        this.userid = userid;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
    }
}
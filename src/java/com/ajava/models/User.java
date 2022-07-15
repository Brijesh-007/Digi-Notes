package com.ajava.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
//Bean class for USer
public class User {

    int uid;
    String name;
    String email;
    String password;
    boolean isVerified;

    public User() {
    }

    public User(String name, String email, String password, boolean isVerified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUid() {
        return uid;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public void setVerified() {
        this.isVerified = true;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDetails(ResultSet set) {
        try {
            this.uid = set.getInt("uid");
            this.name = set.getString("name");
            this.email = set.getString("email");
            this.password = set.getString("password");
            this.isVerified = set.getBoolean("isverified");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

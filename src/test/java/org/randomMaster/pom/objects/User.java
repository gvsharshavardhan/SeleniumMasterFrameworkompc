package org.randomMaster.pom.objects;

import org.randomMaster.pom.utils.JacksonUtils;

import java.io.IOException;

public class User {
    private String userid;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(String userid) throws IOException {
        User[] users = JacksonUtils.deserializeJson("user.json", User[].class);
        for (User user : users) {
            if (user.getUserid().equals(userid)) {
                this.userid = userid;
                this.username = user.getUsername();
                this.password = user.getPassword();
                this.email = user.getEmail();
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
}
package com.nhnacademy.servletboard.domain.user;

public class Admin extends User {

    public Admin(String id, String password, String name) {
        super(id, password, name, "");
    }

    @Override
    public void setProfileFileName(String profileFileName) {
        this.profileFileName = "";
    }
}

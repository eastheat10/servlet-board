package com.nhnacademy.servletboard.domain.user;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {

    protected String id;
    protected String password;
    protected String name;
    protected String profileFileName;

    public User(String id, String password, String name, String profileFileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileFileName = profileFileName;
    }
}

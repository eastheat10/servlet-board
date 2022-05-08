package com.nhnacademy.servletboard.domain.user;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    protected String id;
    protected String password;
    protected String name;
    protected String profileFileName;
}

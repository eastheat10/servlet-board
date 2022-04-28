package com.nhnacademy.servletboard.repository.user;

import com.nhnacademy.servletboard.domain.user.User;
import java.util.List;

public interface UserRepository {

    void add(User user);

    void modify(User user);

    User remove(String id);

    User getUser(String id);

    List<User> getUsers();
}

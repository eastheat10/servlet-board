package com.nhnacademy.servletboard.repository.user;

import com.nhnacademy.servletboard.domain.user.User;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserRepository {

    void add(User user);

    void modify(User user);

    User remove(String id);

    User getUser(String id);

    List<User> getUsers();

    Map<String, User> getMemory();
}

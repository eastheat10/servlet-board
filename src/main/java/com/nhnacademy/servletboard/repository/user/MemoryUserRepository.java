package com.nhnacademy.servletboard.repository.user;

import com.nhnacademy.servletboard.domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {

    private final Map<String, User> memory;

    public MemoryUserRepository() {
        this.memory = new HashMap<>();
    }

    @Override
    public void add(User user) {
        memory.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        memory.put(user.getId(), user);
    }

    @Override
    public User remove(String id) {
        return memory.remove(id);
    }

    @Override
    public User getUser(String id) {
        return memory.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(memory.values());
    }
}

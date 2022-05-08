package com.nhnacademy.servletboard.repository.user;

import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.exception.MemberExistException;
import com.nhnacademy.servletboard.exception.MemberNotExistException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUserRepository implements UserRepository {

    private final Map<String, User> memory;

    public JsonUserRepository(Map<String, User> memory) {
        this.memory = memory;
    }

    @Override
    public void add(User user) {
        if (memory.containsKey(user.getId())) {
            throw new MemberExistException(user.getId());
        }

        memory.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        if (!memory.containsKey(user.getId())) {
            throw new MemberNotExistException();
        }

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

    @Override
    public Map<String, User> getMemory() {
        return memory;
    }
}

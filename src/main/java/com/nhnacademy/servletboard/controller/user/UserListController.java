package com.nhnacademy.servletboard.controller.user;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListController implements Command {

    private final UserRepository userRepository;

    public UserListController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<User> users = userRepository.getUsers();
        req.setAttribute("users", users);

        return "profiles.jsp";
    }
}

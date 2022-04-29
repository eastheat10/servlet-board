package com.nhnacademy.servletboard.controller.user;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteController implements Command {

    private final UserRepository userRepository;

    public UserDeleteController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");

        userRepository.remove(id);

        return "redirect:/";
    }
}

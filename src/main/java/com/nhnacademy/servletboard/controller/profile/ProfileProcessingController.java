package com.nhnacademy.servletboard.controller.profile;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileProcessingController implements Command {

    private final UserRepository userRepository;

    public ProfileProcessingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String profileFileName = req.getParameter("profileFileName");

        User user = new User(id, password, name, profileFileName);
        userRepository.add(user);

        return "redirect:/";
    }
}

package com.nhnacademy.servletboard.controller.user;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateController implements Command {

    private final UserRepository userRepository;

    public UserUpdateController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String profileFileName = req.getParameter("profileFileName");

        User modifiedUser = userRepository.getUser(id);

        modifiedUser.setId(id);
        modifiedUser.setPassword(password);
        modifiedUser.setName(name);
        modifiedUser.setProfileFileName(profileFileName);

        userRepository.modify(modifiedUser);

        return "redirect:/";
    }
}

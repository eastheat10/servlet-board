package com.nhnacademy.servletboard.controller.user;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateFormController implements Command {

    private final UserRepository userRepository;

    public UserUpdateFormController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");

        User updateUser = userRepository.getUser(id);

        req.setAttribute("updateUser", updateUser);

        return "profile-update.jsp";
    }
}

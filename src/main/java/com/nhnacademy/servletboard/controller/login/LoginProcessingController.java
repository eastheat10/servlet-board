package com.nhnacademy.servletboard.controller.login;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.exception.LoginFailException;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginProcessingController implements Command {

    private final UserRepository userRepository;

    public LoginProcessingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        User findUser = userRepository.getUser(id);

        if (Objects.isNull(findUser) || !Objects.equals(findUser.getPassword(), password)) {
            throw new LoginFailException();
        }

        req.getSession(true).setAttribute("user", findUser);

        return "redirect:/";
    }
}

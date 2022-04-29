package com.nhnacademy.servletboard.controller.login;

import com.nhnacademy.servletboard.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/sign/loginForm.jsp";
    }
}

package com.nhnacademy.servletboard.controller.profile;

import com.nhnacademy.servletboard.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/sign/profileForm.jsp";
    }
}

package com.nhnacademy.servletboard.controller.login;

import com.nhnacademy.servletboard.controller.Command;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);

        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user"))) {
            log.info("logout");
            session.invalidate();
        }

        return "redirect:/";
    }
}

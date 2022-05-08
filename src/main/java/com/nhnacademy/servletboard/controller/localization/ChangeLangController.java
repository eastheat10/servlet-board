package com.nhnacademy.servletboard.controller.localization;

import com.nhnacademy.servletboard.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeLangController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String lang = req.getParameter("lang");

        log.info("language: {}", lang);

        req.getServletContext().setAttribute("lang", lang);
        return "/";
    }
}

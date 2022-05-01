package com.nhnacademy.servletboard.controller.listener;

import java.util.Objects;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {

    private int sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        if (Objects.nonNull(se.getSession())) {
            sessionCount++;
            log.info("increase session {}", sessionCount);
            se.getSession().getServletContext().setAttribute("sessionCount", sessionCount);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        if (Objects.nonNull(se.getSession())) {
            sessionCount--;
            log.info("decrease session {}", sessionCount);
            se.getSession().getServletContext().setAttribute("sessionCount", sessionCount);
        }
    }
}

package com.nhnacademy.servletboard.controller.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class WebAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String dir = sce.getServletContext().getRealPath("/counter");
        String counterFile = servletContext.getInitParameter("counterFile");

        String fullPath = dir + File.separator + counterFile;

        File file = new File(fullPath);

        Integer counter = null;
        try (FileInputStream in = new FileInputStream(file)) {
            counter = in.read();
        } catch (IOException e) {
            log.error("", e);
        }

        int count = Optional.ofNullable(counter).orElse(0) == -1 ? 0
            : Optional.ofNullable(counter).orElse(0);

        servletContext.setAttribute("counter", count);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        int counter = (int) servletContext.getAttribute("counter");

        log.info("counter: {}", counter);

        String dir = sce.getServletContext().getRealPath("/counter");
        String counterFile = servletContext.getInitParameter("counterFile");

        String fullPath = dir + File.separator + counterFile;

        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            fos.write(counter);
        } catch (IOException ex) {
            log.error("", ex);
        }
    }
}

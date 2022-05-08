package com.nhnacademy.servletboard.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
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
        log.info("Context Initializer");
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
        log.info("Context Destroy");
        ServletContext servletContext = sce.getServletContext();

        int counter = (int) servletContext.getAttribute("counter");

        log.info("counter: {}", counter);

        String counterDir = sce.getServletContext().getRealPath("/counter");
        String counterFile = servletContext.getInitParameter("counterFile");

        String fullPath = counterDir + File.separator + counterFile;

        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            fos.write(counter);
        } catch (IOException ex) {
            log.error("", ex);
        }

        saveUserData(servletContext);
        savePostData(servletContext);
    }

    private void saveUserData(ServletContext sc) {
        String dbName = (String) sc.getAttribute("userDB");
        String des = sc.getRealPath("db") + File.separator + dbName;

        try (FileOutputStream fos = new FileOutputStream(des)) {
            System.out.println(des);
            ObjectMapper objectMapper = new ObjectMapper();
            UserRepository userRepository = (UserRepository) sc.getAttribute("userRepository");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, userRepository.getMemory());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePostData(ServletContext sc) {
        String dbName = (String) sc.getAttribute("postDB");
        String des = sc.getRealPath("db") + File.separator + dbName;

        try (FileOutputStream fos = new FileOutputStream(des)) {
            ObjectMapper objectMapper = new ObjectMapper();
            PostRepository postRepository = (PostRepository) sc.getAttribute("postRepository");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, postRepository.getMemory());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

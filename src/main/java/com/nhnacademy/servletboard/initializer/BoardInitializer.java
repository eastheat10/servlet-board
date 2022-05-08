package com.nhnacademy.servletboard.initializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.JsonPostRepository;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.JsonUserRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@HandlesTypes({
    javax.servlet.http.HttpServlet.class,
    javax.servlet.Filter.class,
    javax.servlet.ServletContextListener.class,
    javax.servlet.http.HttpSessionListener.class
})
public class BoardInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext)
        throws ServletException {

        log.info("Initialize");

        UserRepository userRepository = new JsonUserRepository(getUserData(servletContext));
        PostRepository postRepository = new JsonPostRepository(getPostData(servletContext));

        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("postRepository", postRepository);
        servletContext.setAttribute("lang", "ko");

        servletContext.setInitParameter("counterFile", "counter.txt");
    }

    private Map<String, User> getUserData(ServletContext servletContext) {

        String dir = servletContext.getRealPath("db");
        String dbName = "userDB.json";

        servletContext.setAttribute("userDB", dbName);

        Map<String, User> map;
        try (FileInputStream fis = new FileInputStream(dir + File.separator + dbName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            map = objectMapper.readValue(fis, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage());
            map = new HashMap<>();
        }
        return Optional.ofNullable(map).orElse(new HashMap<>());
    }

    private Map<Long, Post> getPostData(ServletContext servletContext) {

        String dir = servletContext.getRealPath("db");
        String dbName = "postDB.json";

        servletContext.setAttribute("postDB", dbName);

        Map<Long, Post> map;
        try (FileInputStream fis = new FileInputStream(dir + File.separator + dbName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            map = objectMapper.readValue(fis, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage());
            map = new HashMap<>();
        }
        return Optional.ofNullable(map).orElse(new HashMap<>());
    }
}

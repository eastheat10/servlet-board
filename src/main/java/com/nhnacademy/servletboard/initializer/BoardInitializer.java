package com.nhnacademy.servletboard.initializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            "com.nhnacademy.servletboard.config")) {
            log.info("Initialize");

            UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
            PostRepository postRepository = context.getBean("postRepository", PostRepository.class);

            userRepository.loadMemory(getUserData(servletContext));
            postRepository.setMemory(getPostData(servletContext));

            setSpringBean(servletContext, context);

            servletContext.setAttribute("userRepository", userRepository);
            servletContext.setAttribute("postRepository", postRepository);
            servletContext.setAttribute("lang", "ko");

            servletContext.setInitParameter("counterFile", "counter.txt");
        }
    }

    private Map<String, User> getUserData(ServletContext servletContext) {

        String dir = servletContext.getRealPath("db");
        String dbName = "userDB.json";

        servletContext.setAttribute("userDB", dbName);

        Map<String, User> map = null;
        try (FileInputStream fis = new FileInputStream(dir + File.separator + dbName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            map = objectMapper.readValue(fis, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(map).orElse(new ConcurrentHashMap<>());
    }

    private Map<Long, Post> getPostData(ServletContext servletContext) {

        String dir = servletContext.getRealPath("db");
        String dbName = "postDB.json";

        servletContext.setAttribute("postDB", dbName);

        Map<Long, Post> map = null;
        try (FileInputStream fis = new FileInputStream(dir + File.separator + dbName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            map = objectMapper.readValue(fis, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(map).orElse(new ConcurrentHashMap<>());
    }

    private void setSpringBean(ServletContext servletContext, AnnotationConfigApplicationContext context) {
        servletContext.setAttribute("loginFormController",
            context.getBean("loginFormController", Command.class));
        servletContext.setAttribute("loginProcessingController",
            context.getBean("loginProcessingController", Command.class));
        servletContext.setAttribute("logoutController",
            context.getBean("logoutController", Command.class));
        servletContext.setAttribute("profileFormController",
            context.getBean("profileFormController", Command.class));
        servletContext.setAttribute("profileCreateController",
            context.getBean("profileCreateController", Command.class));
        servletContext.setAttribute("userListController",
            context.getBean("userListController", Command.class));
        servletContext.setAttribute("userController",
            context.getBean("userController", Command.class));
        servletContext.setAttribute("userUpdateFormController",
            context.getBean("userUpdateFormController", Command.class));
        servletContext.setAttribute("userUpdateController",
            context.getBean("userUpdateController", Command.class));
        servletContext.setAttribute("userDeleteController",
            context.getBean("userDeleteController",
            Command.class));
        servletContext.setAttribute("imgController",
            context.getBean("imgController", Command.class));

        servletContext.setAttribute("postFormController",
            context.getBean("postFormController", Command.class));
        servletContext.setAttribute("postCreateController",
            context.getBean("postCreateController", Command.class));
        servletContext.setAttribute("postListController",
            context.getBean("postListController", Command.class));
        servletContext.setAttribute("postController",
            context.getBean("postController", Command.class));
        servletContext.setAttribute("postUpdateFormController",
            context.getBean("postUpdateFormController", Command.class));
        servletContext.setAttribute("postUpdateController",
            context.getBean("postUpdateController", Command.class));
        servletContext.setAttribute("postDeleteController",
            context.getBean("postDeleteController", Command.class));

        servletContext.setAttribute("changeLangController",
            context.getBean("changeLangController", Command.class));

    }
}

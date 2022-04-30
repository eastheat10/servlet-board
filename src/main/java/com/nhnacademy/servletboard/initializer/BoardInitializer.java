package com.nhnacademy.servletboard.initializer;

import com.nhnacademy.servletboard.domain.user.Admin;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.MemoryPostRepository;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.MemoryUserRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
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

        Admin admin = new Admin("admin", "12345", "관리자");
        User user1 = new User("user1", "1234", "사용자1", "/");
        User user2 = new User("user2", "1234", "사용자2", "/");

        UserRepository userRepository = new MemoryUserRepository();
        userRepository.add(admin);
        userRepository.add(user1);
        userRepository.add(user2);
        servletContext.setAttribute("userRepository", userRepository);

        PostRepository postRepository = new MemoryPostRepository();
        servletContext.setAttribute("postRepository", postRepository);

        servletContext.setAttribute("lang", "ko");
    }
}

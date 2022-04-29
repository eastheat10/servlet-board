package com.nhnacademy.servletboard.initializer;

import com.nhnacademy.servletboard.domain.user.Admin;
import com.nhnacademy.servletboard.repository.user.MemoryUserRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

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

        Admin admin = new Admin("admin", "12345", "관리자");
        UserRepository userRepository = new MemoryUserRepository();
        userRepository.add(admin);
        servletContext.setAttribute("userRepository", userRepository);

        servletContext.setAttribute("lang", "ko");
    }
}

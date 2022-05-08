package com.nhnacademy.servletboard.config;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.login.LoginFormController;
import com.nhnacademy.servletboard.controller.login.LoginProcessingController;
import com.nhnacademy.servletboard.controller.login.LogoutController;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginControllerConfiguration {

    private final UserRepository userRepository;

    @Autowired
    public LoginControllerConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public Command loginFormController() {
        return new LoginFormController();
    }

    @Bean
    public Command loginProcessingController() {
        return new LoginProcessingController(userRepository);
    }

    @Bean
    public Command logoutController() {
        return new LogoutController();
    }
}

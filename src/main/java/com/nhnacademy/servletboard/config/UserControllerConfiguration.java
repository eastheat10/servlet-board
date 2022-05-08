package com.nhnacademy.servletboard.config;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.user.ProfileCreateController;
import com.nhnacademy.servletboard.controller.user.ProfileFormController;
import com.nhnacademy.servletboard.controller.user.UserController;
import com.nhnacademy.servletboard.controller.user.UserDeleteController;
import com.nhnacademy.servletboard.controller.user.UserListController;
import com.nhnacademy.servletboard.controller.user.UserUpdateController;
import com.nhnacademy.servletboard.controller.user.UserUpdateFormController;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserControllerConfiguration {

    private final UserRepository userRepository;

    @Autowired
    public UserControllerConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public Command profileCreateController() {
        return new ProfileCreateController(userRepository);
    }

    @Bean
    public Command profileFormController() {
        return new ProfileFormController();
    }

    @Bean
    public Command userController() {
        return new UserController(userRepository);
    }

    @Bean
    public UserDeleteController userDeleteController() {
        return new UserDeleteController(userRepository);
    }

    @Bean
    public Command userListController() {
        return new UserListController(userRepository);
    }

    @Bean
    public Command userUpdateController() {
        return new UserUpdateController(userRepository);
    }

    @Bean
    public Command userUpdateFormController() {
        return new UserUpdateFormController(userRepository);
    }
}

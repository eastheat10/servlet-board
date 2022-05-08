package com.nhnacademy.servletboard.config;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.ImgController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImgControllerConfiguration {

    @Bean
    public Command imgController() {
        return new ImgController();
    }
}

package com.nhnacademy.servletboard.config;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.localization.ChangeLangController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalizationControllerConfiguration {

    @Bean
    public Command changeLangController() {
        return new ChangeLangController();
    }
}

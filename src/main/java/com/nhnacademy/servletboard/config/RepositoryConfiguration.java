package com.nhnacademy.servletboard.config;

import com.nhnacademy.servletboard.repository.post.JsonPostRepository;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.JsonUserRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new JsonUserRepository();
    }

    @Bean
    public PostRepository postRepository() {
        return new JsonPostRepository();
    }
}

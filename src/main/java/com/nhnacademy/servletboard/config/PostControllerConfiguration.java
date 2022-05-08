package com.nhnacademy.servletboard.config;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.post.PostController;
import com.nhnacademy.servletboard.controller.post.PostCreateController;
import com.nhnacademy.servletboard.controller.post.PostDeleteController;
import com.nhnacademy.servletboard.controller.post.PostFormController;
import com.nhnacademy.servletboard.controller.post.PostListController;
import com.nhnacademy.servletboard.controller.post.PostUpdateController;
import com.nhnacademy.servletboard.controller.post.PostUpdateFormController;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostControllerConfiguration {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostControllerConfiguration(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public Command postController() {
        return new PostController(postRepository, userRepository);
    }

    @Bean
    public Command postCreateController() {
        return new PostCreateController(postRepository);
    }

    @Bean
    public Command postDeleteController() {
        return new PostDeleteController(postRepository);
    }

    @Bean
    public Command postFormController() {
        return new PostFormController();
    }

    @Bean
    public Command postListController() {
        return new PostListController(postRepository);
    }

    @Bean
    public Command postUpdateController() {
        return new PostUpdateController(postRepository);
    }

    @Bean
    public Command postUpdateFormController() {
        return new PostUpdateFormController(postRepository);
    }
}

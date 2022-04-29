package com.nhnacademy.servletboard.controller.post;

import static org.mockito.Mockito.*;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

class PostCreateControllerTest {


    Command command;
    PostRepository postRepository;
    Post post;

    HttpServletRequest req;
    HttpServletResponse resp;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        command = new PostCreateController();
        post = mock(Post.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

    }


}
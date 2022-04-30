package com.nhnacademy.servletboard.controller.post;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostListControllerTest {
    Command command;
    PostRepository postRepository;
    Post post;

    HttpServletRequest req;
    HttpServletResponse resp;

    long postId;

    @BeforeEach
    void setUp() {

        postRepository = mock(PostRepository.class);
        command = new PostListController(postRepository);
        post = mock(Post.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        this.postId = 1L;

        when(req.getParameter("postId")).thenReturn(String.valueOf(postId));
        when(postRepository.getPosts()).thenReturn(new ArrayList<>());

        doNothing().when(req).setAttribute(eq("postList"), any(List.class));
    }

    @Test
    @DisplayName("사용자 목록 조회")
    void getUserList() {

        String path = command.execute(req, resp);

        verify(postRepository, times(1)).getPosts();
        assertThat(path).isEqualTo("posts.jsp");
    }
}
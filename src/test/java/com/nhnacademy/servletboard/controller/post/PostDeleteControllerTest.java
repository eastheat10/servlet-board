package com.nhnacademy.servletboard.controller.post;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostDeleteControllerTest {

    Command command;
    PostRepository postRepository;
    Post post;

    HttpServletRequest req;
    HttpServletResponse resp;

    long postId;

    @BeforeEach
    void setUp() {

        postRepository = mock(PostRepository.class);
        command = new PostDeleteController(postRepository);
        post = mock(Post.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        this.postId = 1L;

        when(req.getParameter("id")).thenReturn(String.valueOf(postId));
        when(postRepository.remove(anyLong())).thenReturn(post);

        doNothing().when(req).setAttribute(anyString(), any());
    }

    @Test
    @DisplayName("게시물 삭제")
    void remove() {

        String path = command.execute(req, resp);

        verify(postRepository).remove(anyLong());
        verify(req).getParameter("id");
        assertThat(path).startsWith("redirect");
    }
}
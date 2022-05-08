package com.nhnacademy.servletboard.controller.post;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostUpdateControllerTest {

    Command command;
    PostRepository postRepository;
    Post post;

    HttpServletRequest req;
    HttpServletResponse resp;

    long id;

    String title;
    String content;
    String writerUserId;
    String writeTime;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        command = new PostUpdateController(postRepository);
        post = mock(Post.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        this.id = 1L;
        this.title = "title";
        this.content = "content";
        this.writerUserId = "writerUserId";
        this.writeTime = LocalDateTime.now().toString();

        when(req.getParameter("id")).thenReturn(String.valueOf(id));
        when(req.getParameter("title")).thenReturn(title);
        when(req.getParameter("content")).thenReturn(content);
        when(postRepository.getPost(anyLong())).thenReturn(post);

        doNothing().when(postRepository).modify(post);
    }

    @Test
    @DisplayName("게시물 수정")
    void modify() {

        String path = command.execute(req, resp);

        verify(postRepository, times(1)).getPost(anyLong());
        verify(postRepository, times(1)).modify(post);
        assertThat(path).startsWith("redirect");
    }
}
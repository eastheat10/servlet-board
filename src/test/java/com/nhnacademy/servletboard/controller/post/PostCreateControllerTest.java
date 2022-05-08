package com.nhnacademy.servletboard.controller.post;

import static org.mockito.Mockito.*;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostCreateControllerTest {

    Command command;
    PostRepository postRepository;
    Post post;
    User user;

    HttpServletRequest req;
    HttpServletResponse resp;

    long postId = 1L;

    String title;
    String content;
    String writerUserId;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        command = new PostCreateController(postRepository);
        post = mock(Post.class);
        user = mock(User.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        this.title = "title";
        this.content = "content";
        this.writerUserId = "writerUserId";

        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("title")).thenReturn(title);
        when(req.getParameter("content")).thenReturn(content);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(user.getId()).thenReturn(writerUserId);

        when(postRepository.register(post)).thenReturn(postId);
    }

    @Test
    @DisplayName("게시물 등록")
    void createPost() {

        String path = command.execute(req, resp);

        verify(postRepository, times(1)).register(any());
        Assertions.assertThat(path).startsWith("redirect");
    }
}
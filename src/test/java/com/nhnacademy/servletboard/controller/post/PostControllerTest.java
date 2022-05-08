package com.nhnacademy.servletboard.controller.post;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostControllerTest {

    Command command;
    PostRepository postRepository;
    UserRepository userRepository;
    Post post;
    User user;

    HttpServletRequest req;
    HttpServletResponse resp;

    long postId;

    @BeforeEach
    void setUp() {

        postRepository = mock(PostRepository.class);
        userRepository = mock(UserRepository.class);
        command = new PostController(postRepository, userRepository);
        post = mock(Post.class);
        user = mock(User.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        this.postId = 1L;

        // TODO
        when(req.getParameter("id")).thenReturn(String.valueOf(postId));
        when(postRepository.getPost(anyLong())).thenReturn(post);
        when(post.getWriterUserId()).thenReturn("userId");
        when(userRepository.getUser("userId")).thenReturn(user);

        when(user.getProfileFileName()).thenReturn("profileName");

        doNothing().when(req).setAttribute(eq("findPost"), any());
        doNothing().when(req).setAttribute(eq("profile"), eq("profileName"));
    }

    @Test
    @DisplayName("게시물 하나 찾기")
    void findPostById() {

        String path = command.execute(req, resp);

        verify(postRepository, times(1)).getPost(anyLong());
        verify(req, times(1)).setAttribute("findPost", post);
        verify(req, times(1)).setAttribute("profile", "profileName");

        assertThat(path).isEqualTo("post.jsp");
    }
}
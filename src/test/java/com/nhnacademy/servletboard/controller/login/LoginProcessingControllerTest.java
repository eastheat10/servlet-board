package com.nhnacademy.servletboard.controller.login;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.exception.LoginFailException;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginProcessingControllerTest {

    LoginProcessingController controller;
    UserRepository userRepository;
    User user;

    HttpServletRequest request;
    HttpServletResponse response;

    String id;
    String password;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        userRepository = mock(UserRepository.class);
        controller = new LoginProcessingController(userRepository);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        id = "id";
        password = "password";
        when(request.getParameter("id")).thenReturn(id);
        when(request.getParameter("password")).thenReturn(password);
        when(userRepository.getUser(id)).thenReturn(user);
        when(user.getPassword()).thenReturn(password);
        when(request.getSession(true)).thenReturn(session);
        doNothing().when(session).setAttribute(anyString(), any());
    }

    @Test
    @DisplayName("로그인")
    void execute() {
        String path = controller.execute(request, response);

        when(userRepository.getUser(id)).thenReturn(user);

        assertThat(path).startsWith("redirect");
    }

    @Test
    @DisplayName("아이디와 일치하는 회원 없음")
    void memberNotExist() {

        when(userRepository.getUser(id)).thenReturn(null);

        assertThatThrownBy(() -> controller.execute(request, response))
            .isInstanceOf(LoginFailException.class);
    }

    @Test
    @DisplayName("비밀번호 틀림")
    void wrongPassword() {

        when(user.getPassword()).thenReturn("");

        assertThatThrownBy(() -> controller.execute(request, response))
            .isInstanceOf(LoginFailException.class);
    }

}
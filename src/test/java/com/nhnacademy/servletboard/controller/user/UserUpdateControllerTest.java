package com.nhnacademy.servletboard.controller.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserUpdateControllerTest {

    Command command;
    UserRepository userRepository;
    User user;

    HttpServletRequest req;
    HttpServletResponse resp;

    String id;
    String password;
    String name;
    String profileFileName;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        command = new UserUpdateController(userRepository);
        user = mock(User.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        id = "id";
        password = "password";
        name = "name";
        profileFileName = "/upload";

        doNothing().when(userRepository).modify(any());
        doNothing().when(user).setId(id);
        doNothing().when(user).setPassword(password);
        doNothing().when(user).setName(name);
        doNothing().when(user).setProfileFileName(profileFileName);

        when(userRepository.getUser(id)).thenReturn(user);
        when(req.getParameter("id")).thenReturn(id);
        when(req.getParameter("password")).thenReturn(password);
        when(req.getParameter("name")).thenReturn(name);
        when(req.getParameter("profileFileName")).thenReturn(profileFileName);
    }

    @Test
    @DisplayName("사용자 수정")
    void modify() {

        String path = command.execute(req, resp);

        verify(userRepository, times(1)).getUser(id);
        verify(userRepository, times(1)).modify(user);
        assertThat(path).startsWith("redirect");
    }
}
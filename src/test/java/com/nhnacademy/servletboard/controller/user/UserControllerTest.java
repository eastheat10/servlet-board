package com.nhnacademy.servletboard.controller.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserControllerTest {

    Command command;
    UserRepository userRepository;
    User user;

    HttpServletRequest req;
    HttpServletResponse resp;

    String id;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        command = new UserController(userRepository);
        user = mock(User.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        id = "id";

        when(req.getParameter("id")).thenReturn(id);
        when(userRepository.getUser(id)).thenReturn(user);
        doNothing().when(req).setAttribute("findUser", user);
    }

    @Test
    @DisplayName("사용자 한 명 조회")
    void findUserById() {
        String path = command.execute(req, resp);


        verify(userRepository, times(1)).getUser(id);
        assertThat(path).startsWith("/");
    }
}
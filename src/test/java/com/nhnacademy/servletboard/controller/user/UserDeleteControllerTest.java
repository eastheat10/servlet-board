package com.nhnacademy.servletboard.controller.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
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

class UserDeleteControllerTest {

    Command command;
    UserRepository userRepository;
    User user;

    HttpServletRequest req;
    HttpServletResponse resp;

    String id;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        command = new UserDeleteController(userRepository);
        user = mock(User.class);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        id = "id";

        when(req.getParameter("id")).thenReturn(id);
        when(userRepository.remove(anyString())).thenReturn(user);
    }

    @Test
    @DisplayName("사용자 삭제")
    void delete() {

        String path = command.execute(req, resp);

        verify(userRepository, times(1)).remove(id);
        assertThat(path).startsWith("redirect");
    }

}
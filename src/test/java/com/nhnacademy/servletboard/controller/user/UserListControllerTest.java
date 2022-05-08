package com.nhnacademy.servletboard.controller.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserListControllerTest {

    Command command;
    UserRepository userRepository;

    HttpServletRequest req;
    HttpServletResponse resp;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        command = new UserListController(userRepository);

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        when(userRepository.getUsers()).thenReturn(new ArrayList<>());
        doNothing().when(req).setAttribute(anyString(), any());
    }

    @Test
    @DisplayName("사용자 목록 조회")
    void display() {

        String path = command.execute(req, resp);

        verify(userRepository, times(1)).getUsers();
        assertThat(path).startsWith("/");
    }
}
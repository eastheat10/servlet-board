package com.nhnacademy.servletboard.controller.profile;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfileProcessingControllerTest {

    Command command;
    UserRepository repository;

    HttpServletRequest req;
    HttpServletResponse resp;

    String id;
    String password;
    String name;
    String profileFileName;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        command = new ProfileProcessingController(repository);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);

        id = "id";
        password = "password";
        name = "name";
        profileFileName = "/profile";

        when(req.getParameter("id")).thenReturn(id);
        when(req.getParameter("password")).thenReturn(password);
        when(req.getParameter("name")).thenReturn(name);
        when(req.getParameter("profileFileName")).thenReturn(profileFileName);
    }

    @Test
    @DisplayName("회원가입 진행")
    void signin() {

        String path = command.execute(req, resp);

        verify(repository, times(1)).add(any());
        assertThat(path).startsWith("redirect");
    }

}
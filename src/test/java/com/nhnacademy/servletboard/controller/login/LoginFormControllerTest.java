package com.nhnacademy.servletboard.controller.login;

import static org.mockito.Mockito.*;

import com.nhnacademy.servletboard.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginFormControllerTest {

    @Test
    @DisplayName("로그인 폼 진입")
    void execute() {
        Command command = new LoginFormController();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        Assertions.assertThat(command.execute(req, resp)).isEqualTo("/sign/loginForm.jsp");
    }
}
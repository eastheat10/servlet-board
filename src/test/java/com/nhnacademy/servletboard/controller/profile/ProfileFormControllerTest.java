package com.nhnacademy.servletboard.controller.profile;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.user.ProfileFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProfileFormControllerTest {

    @Test
    @DisplayName("회원가입 폼 진입")
    void execute() {
        Command command = new ProfileFormController();

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Assertions.assertThat(command.execute(request, response)).isEqualTo("/sign/profile-form.jsp");
    }
}
package com.nhnacademy.servletboard.controller.post;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.controller.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostFormControllerTest {

    Command command;

    HttpServletRequest req;
    HttpServletResponse resp;

    @BeforeEach
    void setUp() {

        command = new PostFormController();

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
    }

    @Test
    @DisplayName("게시물 작성 폼으로 이동")
    void postForm() {
        assertThat(command.execute(req, resp)).isEqualTo("post-form.jsp");
    }
}
package com.nhnacademy.servletboard.domain.post;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    @DisplayName("게시물 조회수 증가")
    void increaseViewCount() {
        String title = "title";
        String content = "content";
        String writeUserId = "user";

        Post post = spy(new Post(title, content, writeUserId));

        post.increaseViewCount();

        verify(post, times(post.getViewCount())).increaseViewCount();
    }
}
package com.nhnacademy.servletboard.domain.post;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    @DisplayName("게시물 조회수 증가")
    void increaseViewCount() {
        long id = 1L;
        String title = "title";
        String content = "content";
        String writeUserId = "user";
        LocalDateTime writeTime = LocalDateTime.now();

        Post post = spy(new Post(id, title, content, writeUserId, writeTime));

        post.increaseViewCount();

        verify(post, times(post.getViewCount())).increaseViewCount();
    }
}
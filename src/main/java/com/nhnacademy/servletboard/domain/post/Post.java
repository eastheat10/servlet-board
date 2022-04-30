package com.nhnacademy.servletboard.domain.post;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post implements Serializable {

    private long id;
    private String title;
    private String content;
    private String writerUserId;
    private LocalDateTime writeTime;
    private int viewCount;

    public Post(String title, String content, String writerUserId) {
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now().withNano(0);
        this.viewCount = 1;
    }

    public void increaseViewCount() {
        viewCount++;
    }
}

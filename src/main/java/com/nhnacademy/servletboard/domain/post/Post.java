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
    private String write\rUserId;
    private LocalDateTime writeTime;
    private int viewCount;

    public Post(long id, String title, String content, String writerUserId,
                LocalDateTime writeTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = writeTime;
        this.viewCount = 0;
    }

    void increaseViewCount() {
        viewCount++;
    }
}

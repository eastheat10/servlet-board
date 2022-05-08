package com.nhnacademy.servletboard.repository.post;

import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.page.Page;
import java.util.List;
import java.util.Map;

public interface PostRepository {

    long register(Post post);

    void modify(Post post);

    Post remove(long id);

    Post getPost(long id);

    List<Post> getPosts();

    Page<Post> getPagedPosts(int page, int size);

    Map<Long, Post> getMemory();
}

package com.nhnacademy.servletboard.repository.post;

import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.exception.PostNotExistException;
import com.nhnacademy.servletboard.page.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonPostRepository implements PostRepository {

    private long postId;
    private final Map<Long, Post> memory;

    public JsonPostRepository(Map<Long, Post> memory) {
        this.memory = memory;
        postId = memory.values()
                       .stream()
                       .map(Post::getId)
                       .max((i, j) -> (int) (i - j))
                       .orElse(1L) + 1;
    }

    @Override
    public long register(Post post) {

        long newPostId = postId++;

        post.setId(newPostId);
        memory.put(newPostId, post);

        return newPostId;
    }

    @Override
    public void modify(Post post) {

        checkPost(post.getId());
        memory.put(post.getId(), post);
    }

    @Override
    public Post remove(long id) {

        checkPost(id);

        return memory.remove(id);
    }

    @Override
    public Post getPost(long id) {

        checkPost(id);

        return memory.get(id);
    }

    @Override
    public List<Post> getPosts() {

        return new ArrayList<>(memory.values());
    }

    private void checkPost(long postId) {
        if (!memory.containsKey(postId)) {
            throw new PostNotExistException();
        }
    }

    @Override
    public Page<Post> getPagedPosts(int page, int size) {
        return new Page<>(new ArrayList<>(memory.values()), page, size);
    }

    @Override
    public Map<Long, Post> getMemory() {
        return memory;
    }
}

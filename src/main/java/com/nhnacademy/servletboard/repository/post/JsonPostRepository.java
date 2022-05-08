package com.nhnacademy.servletboard.repository.post;

import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.exception.PostNotExistException;
import com.nhnacademy.servletboard.page.Page;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class JsonPostRepository implements PostRepository {

    private Map<Long, Post> memory;

    @Override
    public long register(Post post) {

        long newPostId = memory.values()
                               .stream()
                               .map(Post::getId)
                               .max(Comparator.comparingLong(i -> i))
                               .orElse(0L) + 1;

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

    @Override
    public void setMemory(Map<Long, Post> memory) {
        this.memory = memory;
    }
}

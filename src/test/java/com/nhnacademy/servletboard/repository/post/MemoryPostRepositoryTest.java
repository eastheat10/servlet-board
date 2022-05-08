package com.nhnacademy.servletboard.repository.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.exception.PostNotExistException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryPostRepositoryTest {

    PostRepository postRepository;
    Post post;
    long postId;

    @BeforeEach
    void setUp() {
        postRepository = new MemoryPostRepository();
        post = mock(Post.class);
        postId = 1L;
        when(post.getId()).thenReturn(postId);
    }

    @Test
    @DisplayName("게시물 등록")
    void register() {

        long registerId = postRepository.register(post);

        assertThat(registerId).isEqualTo(postId);
        verify(post, times(1)).setId(postId);
    }

    @Test
    @DisplayName("존재하지 않는 게시물 수정")
    void modify_fail() {

        assertThatThrownBy(() -> postRepository.modify(post))
            .isInstanceOf(PostNotExistException.class);
    }

    @Test
    @DisplayName("게시물 수정")
    void modify() {

        postRepository.register(post);

        postRepository.modify(post);

        verify(post, times(2)).getId();
    }

    @Test
    @DisplayName("존재하지 않는 게시물 삭제")
    void remove_fail() {
        assertThatThrownBy(() -> postRepository.remove(postId))
            .isInstanceOf(PostNotExistException.class);
    }

    @Test
    @DisplayName("게시물 삭제")
    void remove() {
        postRepository.register(post);

        long id = post.getId();

        Post removedPost = postRepository.remove(id);

        assertThat(removedPost).isEqualTo(post);
    }

    @Test
    @DisplayName("존재하지 않는 게시물 조회")
    void getPost_fail() {
        assertThatThrownBy(() -> postRepository.getPost(postId))
            .isInstanceOf(PostNotExistException.class);
    }

    @Test
    @DisplayName("게시물 조회")
    void getPost() {

        postRepository.register(post);

        Post findPost = postRepository.getPost(this.post.getId());

        assertThat(findPost).isEqualTo(post);
    }

    @Test
    @DisplayName("게시물 목록 조회")
    void getPosts() {

        doNothing().when(post).setId(anyLong());

        postRepository.register(post);

        List<Post> posts = postRepository.getPosts();

        verify(post, times(posts.size())).setId(anyLong());
    }
}
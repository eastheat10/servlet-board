package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUpdateController implements Command {

    private final PostRepository postRepository;

    public PostUpdateController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long postId = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Post updatePost = postRepository.getPost(postId);

        updatePost.setTitle(title);
        updatePost.setContent(content);

        postRepository.modify(updatePost);

        return "redirect:post.do?id=" + updatePost.getId();
    }
}

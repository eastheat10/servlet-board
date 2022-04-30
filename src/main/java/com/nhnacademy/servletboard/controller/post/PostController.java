package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostController implements Command {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long postId = Long.parseLong(req.getParameter("id"));

        Post findPost = postRepository.getPost(postId);

        req.setAttribute("findPost", findPost);

        return "post.jsp";
    }
}

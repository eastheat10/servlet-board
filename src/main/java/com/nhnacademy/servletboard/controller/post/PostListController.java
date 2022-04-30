package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostListController implements Command {

    private final PostRepository postRepository;

    public PostListController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        req.setAttribute("postList", postRepository.getPosts());

        return "posts.jsp";
    }
}

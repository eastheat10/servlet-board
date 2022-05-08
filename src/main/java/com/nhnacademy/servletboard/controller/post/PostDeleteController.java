package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDeleteController implements Command {

    private final PostRepository postRepository;

    public PostDeleteController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long postId = Long.parseLong(req.getParameter("id"));

        postRepository.remove(postId);

        return "redirect:/";
    }
}

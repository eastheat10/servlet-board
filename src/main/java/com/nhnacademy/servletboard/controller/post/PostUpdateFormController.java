package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUpdateFormController implements Command {

    private final PostRepository postRepository;

    public PostUpdateFormController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long id = Long.parseLong(req.getParameter("id"));
        Post updatePost = postRepository.getPost(id);

        req.setAttribute("updatePost", updatePost);

        return "post-update.jsp";
    }
}

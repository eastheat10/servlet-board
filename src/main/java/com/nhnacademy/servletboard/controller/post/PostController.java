package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostController implements Command {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long postId = Long.parseLong(req.getParameter("id"));

        Post findPost = postRepository.getPost(postId);

        User writer = userRepository.getUser(findPost.getWriterUserId());

        req.setAttribute("findPost", findPost);
        req.setAttribute("profile", writer.getProfileFileName());

        return "post.jsp";
    }
}

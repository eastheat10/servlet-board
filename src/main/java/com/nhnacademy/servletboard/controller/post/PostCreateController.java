package com.nhnacademy.servletboard.controller.post;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.post.Post;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCreateController implements Command {

    private final PostRepository postRepository;

    public PostCreateController(
        PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User writer = (User) req.getSession().getAttribute("user");

        Post createPost = new Post(title, content, writer.getId());

        long postId = postRepository.register(createPost);

        return "redirect:post.do?id=" + postId;
    }
}

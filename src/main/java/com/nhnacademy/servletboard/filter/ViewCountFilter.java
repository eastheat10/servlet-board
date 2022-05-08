package com.nhnacademy.servletboard.filter;

import com.nhnacademy.servletboard.domain.post.Post;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "viewCountFilter", urlPatterns = {"/post/post.do"})
public class ViewCountFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);

        Post findPost = (Post) servletRequest.getAttribute("findPost");
        findPost.increaseViewCount();
        log.info("view count increase id:{}, count: {}", findPost.getId(), findPost.getViewCount());
    }
}

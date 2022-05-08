package com.nhnacademy.servletboard.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "counterFilter", urlPatterns = {
    "/post/post.do", "/user/user.do"
})
public class CounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        ServletContext servletContext = servletRequest.getServletContext();
        Integer counter = (Integer) servletContext.getAttribute("counter");

        log.info("방문자 수: {}", ++counter);

        servletContext.setAttribute("counter", counter);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

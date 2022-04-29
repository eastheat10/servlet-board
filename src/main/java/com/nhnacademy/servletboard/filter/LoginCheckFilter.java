package com.nhnacademy.servletboard.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String requestUri = request.getRequestURI();

        log.info("request: {}", requestUri);

        if (isWhiteList(request.getRequestURI())) {
            HttpSession session = request.getSession(false);
            requestUri = request.getRequestURI();
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
                ((HttpServletResponse) servletResponse).sendRedirect("/sign/loginForm.jsp");
                return;
            }
        }

        if (!requestUri.startsWith("/login")) {
            servletRequest.setAttribute("from", requestUri);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String uri) {
        return !uri.equals("/logout.do")
            && !uri.equals("/sign/loginForm.jsp")
            && !uri.equals("/login.do")
            && !uri.equals("/change-lang.do");
    }
}

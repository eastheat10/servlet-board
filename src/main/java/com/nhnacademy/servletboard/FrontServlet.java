package com.nhnacademy.servletboard;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.ImgController;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String GET = "GET";
    private static final String POST = "POST";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {

            Command command = resolveServlet(req);

            String view = command.execute(req, resp);

            if (command instanceof ImgController) {
                return;
            }
            if (view.startsWith(REDIRECT_PREFIX)) {
                // REDIRECT
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // FORWARD
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            log.error("ERROR: {}", e);
            req.setAttribute("exception", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/error/error.jsp");
            rd.forward(req, resp);
        }
    }

    private Command resolveServlet(HttpServletRequest req) {

        ServletContext servletContext = req.getServletContext();
        String servletPath = req.getServletPath();
        String method = req.getMethod();

        log.info("path: {}", servletPath);

        Command command = null;

        // LOGIN
        if ("/login.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("loginFormController");
        } else if ("/login.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("loginProcessingController");
        } else if ("/logout.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("logoutController");
        }

        // PROFILE
        else if ("/user/profile.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("profileFormController");
        } else if ("/user/profile.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("profileCreateController");
        } else if ("/user/profiles.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("userListController");
        } else if ("/user/user.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("userController");
        } else if ("/user/update.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("userUpdateFormController");
        } else if ("/user/update.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("userUpdateController");
        } else if ("/user/delete.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("userDeleteController");
        } else if ("/img.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("imgController");
        }

        // POST
        else if ("/post/create.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postFormController");
        } else if ("/post/create.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postCreateController");
        } else if ("/post/list.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postListController");
        } else if ("/post/post.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postController");
        } else if ("/post/update.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postUpdateFormController");
        } else if ("/post/update.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postUpdateController");
        } else if ("/post/delete.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = (Command) servletContext.getAttribute("postDeleteController");
        }

        // LOCALIZATION
        else if ("/change-lang.do".equals(servletPath)) {
            command = (Command) servletContext.getAttribute("changeLangController");
        }

        return command;
    }
}

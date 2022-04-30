package com.nhnacademy.servletboard;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.ImgController;
import com.nhnacademy.servletboard.controller.localization.ChangeLangController;
import com.nhnacademy.servletboard.controller.login.LoginFormController;
import com.nhnacademy.servletboard.controller.login.LoginProcessingController;
import com.nhnacademy.servletboard.controller.login.LogoutController;
import com.nhnacademy.servletboard.controller.post.PostController;
import com.nhnacademy.servletboard.controller.post.PostCreateController;
import com.nhnacademy.servletboard.controller.post.PostDeleteController;
import com.nhnacademy.servletboard.controller.post.PostFormController;
import com.nhnacademy.servletboard.controller.post.PostListController;
import com.nhnacademy.servletboard.controller.post.PostUpdateController;
import com.nhnacademy.servletboard.controller.post.PostUpdateFormController;
import com.nhnacademy.servletboard.controller.user.ProfileCreateController;
import com.nhnacademy.servletboard.controller.user.ProfileFormController;
import com.nhnacademy.servletboard.controller.user.UserController;
import com.nhnacademy.servletboard.controller.user.UserDeleteController;
import com.nhnacademy.servletboard.controller.user.UserListController;
import com.nhnacademy.servletboard.controller.user.UserUpdateController;
import com.nhnacademy.servletboard.controller.user.UserUpdateFormController;
import com.nhnacademy.servletboard.repository.post.PostRepository;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

        String servletPath = req.getServletPath();
        String method = req.getMethod();

        log.info("path: {}", servletPath);

        UserRepository userRepository =
            (UserRepository) getServletContext().getAttribute("userRepository");

        PostRepository postRepository =
            (PostRepository) getServletContext().getAttribute("postRepository");

        Command command = null;

        // LOGIN
        if ("/login.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new LoginFormController();
        } else if ("/login.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = new LoginProcessingController(userRepository);
        } else if ("/logout.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new LogoutController();
        }

        // PROFILE
        else if ("/user/profile.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new ProfileFormController();
        } else if ("/user/profile.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = new ProfileCreateController(userRepository);
        } else if ("/user/profiles.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new UserListController(userRepository);
        } else if ("/user/user.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new UserController(userRepository);
        } else if ("/user/update.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new UserUpdateFormController(userRepository);
        } else if ("/user/update.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = new UserUpdateController(userRepository);
        } else if ("/user/delete.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new UserDeleteController(userRepository);
        } else if ("/user/img.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new ImgController(userRepository);
        }

        // POST
        else if ("/post/create.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new PostFormController();
        } else if ("/post/create.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = new PostCreateController(postRepository);
        } else if ("/post/list.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new PostListController(postRepository);
        } else if ("/post/post.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new PostController(postRepository);
        } else if ("/post/update.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new PostUpdateFormController(postRepository);
        } else if ("/post/update.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = new PostUpdateController(postRepository);
        } else if ("/post/delete.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new PostDeleteController(postRepository);
        }

        // LOCALIZATION
        else if ("/change-lang.do".equals(servletPath)) {
            command = new ChangeLangController();
        }

        return command;
    }
}

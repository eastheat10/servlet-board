package com.nhnacademy.servletboard;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.controller.localization.ChangeLangController;
import com.nhnacademy.servletboard.controller.login.LoginFormController;
import com.nhnacademy.servletboard.controller.login.LoginProcessingController;
import com.nhnacademy.servletboard.controller.login.LogoutController;
import com.nhnacademy.servletboard.controller.profile.ProfileFormController;
import com.nhnacademy.servletboard.controller.profile.ProfileProcessingController;
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

            if (view.startsWith(REDIRECT_PREFIX)) {
                // REDIRECT
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // FORWARD
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            req.setAttribute("exception", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private Command resolveServlet(HttpServletRequest req) {

        String servletPath = req.getServletPath();
        String method = req.getMethod();

        UserRepository userRepository =
            (UserRepository) getServletContext().getAttribute("userRepository");

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
        else if ("/profile.do".equals(servletPath) && GET.equalsIgnoreCase(method)) {
            command = new ProfileFormController();
        } else if ("/profile.do".equals(servletPath) && POST.equalsIgnoreCase(method)) {
            command = new ProfileProcessingController(userRepository);
        }

        // LOCALIZATION
        else if ("/change-lang.do".equals(servletPath)) {
            command = new ChangeLangController();
        }

        return command;
    }
}

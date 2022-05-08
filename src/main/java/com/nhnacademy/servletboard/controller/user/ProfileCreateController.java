package com.nhnacademy.servletboard.controller.user;

import com.nhnacademy.servletboard.controller.Command;
import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MultipartConfig(
    location = "/tmp",
    maxFileSize = -1L,
    maxRequestSize = -1L,
    fileSizeThreshold = 1024
)
public class ProfileCreateController implements Command {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    private final UserRepository userRepository;

    public ProfileCreateController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String uploadDir = req.getServletContext().getRealPath("/upload/profile_img");

        String id = "";
        String password = "";
        String name = "";
        String fileName = "";

        try {
            for (Part part : req.getParts()) {
                String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

                if (contentDisposition.contains("filename=")) {
                    fileName = extractFileName(contentDisposition);
                    log.info("fileName: {}", fileName);
                    if (part.getSize() > 0) {
                        log.info("file dir: {}", uploadDir + File.separator + fileName);
                        part.write(uploadDir + File.separator + fileName);
                        part.delete();
                    }
                } else {
                    String formValue = req.getParameter(part.getName());
                    if ((part.getName()).equals("id")) {
                        id = formValue;
                    } else if ((part.getName()).equals("password")) {
                        password = formValue;
                    } else if ((part.getName()).equals("name")) {
                        name = formValue;
                    }
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        User user = new User(id, password, name, fileName);
        userRepository.add(user);

        return "redirect:user.do?id=" + id;
    }

    private String extractFileName(String contentDisposition) {
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 1).trim().replace("\"", "");
                int index = fileName.lastIndexOf(File.separator);
                return fileName.substring(index + 1);
            }
        }

        return null;
    }
}

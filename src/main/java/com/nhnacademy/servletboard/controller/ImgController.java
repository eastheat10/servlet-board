package com.nhnacademy.servletboard.controller;

import com.nhnacademy.servletboard.repository.user.UserRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImgController implements Command {

    private final UserRepository userRepository;

    public ImgController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        try (ServletOutputStream out = resp.getOutputStream()) {
            resp.setContentType("image/*");

            String profileName = req.getParameter("img");
            String uploadDir = req.getServletContext().getRealPath("/upload/profile_img");
            String fullPath = uploadDir + File.separator + profileName;

            log.info("path: {}", fullPath);

            File profile = new File(fullPath);
            Path profilePath = Path.of(fullPath);

            FileInputStream in = new FileInputStream(profile);

            byte[] buf = new byte[(int) Files.size(profilePath)];

            in.read(buf);
            out.write(buf);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

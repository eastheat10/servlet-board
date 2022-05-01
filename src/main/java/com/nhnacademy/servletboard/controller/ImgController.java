package com.nhnacademy.servletboard.controller;

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

            try (FileInputStream in = new FileInputStream(profile)) {

                byte[] buf = new byte[(int) Files.size(profilePath)];

                int t;
                while ((t = in.read(buf)) > 0) {
                    out.write(buf, 0, t);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

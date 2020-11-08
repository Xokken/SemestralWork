package ru.itis.xokken.Servlets;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.Services.UserServiceImpl;
import ru.itis.xokken.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;

@MultipartConfig
@WebServlet("/gates/upload")
public class ServletUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "C:\\Users\\Xokken\\IdeaProjects\\hostImgSem\\hostImg\\";
        HttpSession session = request.getSession();// Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = session.getAttribute("email") + "1.jpg"; // MSIE fix.

        OutputStream out = null;
        InputStream filecontent = null;
        PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }

            UserServiceImpl userService = new UserServiceImpl(new UserRepImp(), new BCryptPasswordEncoder());
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            userService.updateImg(userDTO, "/hostImg/" + fileName);
            userDTO.setImg("/hostImg/" + fileName);
            session.setAttribute("user", userDTO);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

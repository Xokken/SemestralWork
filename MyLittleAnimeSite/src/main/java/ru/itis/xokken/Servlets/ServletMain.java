package ru.itis.xokken.Servlets;

import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Repositories.WorldRepImp;
import ru.itis.xokken.Services.WorldServiceImpl;
import ru.itis.xokken.dto.UserDTO;
import ru.itis.xokken.dto.WorldDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/gates/home")
public class ServletMain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        WorldServiceImpl worldService = new WorldServiceImpl(new WorldRepImp());
        List<WorldDTO> worldDTOS = worldService.showAllWorlds();
        session.setAttribute("worldList", worldDTOS);
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("name", user.getName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("age", user.getAge());
        session.setAttribute("img", user.getImg());
        request.getRequestDispatcher("/JSPResources/MainPage.jsp").forward(request, response);
    }
}

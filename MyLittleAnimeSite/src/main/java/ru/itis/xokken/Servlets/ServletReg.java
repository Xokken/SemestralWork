package ru.itis.xokken.Servlets;

import lombok.var;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.Services.UserService;
import ru.itis.xokken.Services.UserServiceImpl;
import ru.itis.xokken.Utility.CheckSignAndLog;
import ru.itis.xokken.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class ServletReg extends HttpServlet {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckSignAndLog checkSignAndLog = new CheckSignAndLog(request);
        HttpSession session = request.getSession();
        if (checkSignAndLog.checkCorrect()) {
            UserDTO userDTO = checkSignAndLog.returnUserDTO();
            UserServiceImpl userService = new UserServiceImpl(new UserRepImp(), passwordEncoder);
            if (userDTO.getName() == null){
                if (userService.checkUser(userDTO) != null){
                    userDTO = userService.checkUser(userDTO);
                    doRed(session, response, userDTO);
                }
                else {
                    doReg(request, response);
                }
            }
            else if(userService.add(userDTO) && (userDTO.getAge() != -1)){
                doRed(session, response, userDTO);
            }
            else {
                doReg(request, response);
            }
        }
        else {
            doReg(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/JSPResources/RegistrationPage.jsp").forward(request, response);
    }

    private void doRed(HttpSession session, HttpServletResponse response, UserDTO userDTO) throws IOException {
        session.setAttribute("auth", true);
        session.setAttribute("user", userDTO);
        session.setAttribute("admin", userDTO.isLaw());
        response.sendRedirect(getServletContext().getContextPath() + "/gates/home");
    }

    private void doReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "Введённые данные некорректны");
        request.getSession().setAttribute("auth", false);
        doGet(request, response);
    }

}

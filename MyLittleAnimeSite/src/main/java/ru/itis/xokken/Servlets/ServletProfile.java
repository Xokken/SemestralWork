package ru.itis.xokken.Servlets;

import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Repositories.PersonsRepImp;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.Services.PersonServiceImpl;
import ru.itis.xokken.dto.PersonDTO;
import ru.itis.xokken.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;




@MultipartConfig
@WebServlet("/gates/profile")
public class ServletProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("delete");

        if (name != null){
            PersonDTO personDTO = null;
            for (PersonDTO el: (List<PersonDTO>) request.getSession().getAttribute("personList")) {
                if (el.getName().equals(name)){
                    personDTO = el;
                    break;
                }
            }
            PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
            personService.deletePersonUsers((UserDTO) request.getSession().getAttribute("user"), personDTO);
        }
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        session.setAttribute("name", userDTO.getName());
        session.setAttribute("email", userDTO.getEmail());
        session.setAttribute("age", userDTO.getAge());
        session.setAttribute("img", userDTO.getImg());
        PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
        List<PersonDTO> personDTOS = personService.showAllPersonByUser(userDTO);
        session.setAttribute("personList", personDTOS);
        System.out.println(userDTO);
        request.getRequestDispatcher("/JSPResources/ProfilePage.jsp").forward(request, response);
    }

}

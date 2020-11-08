package ru.itis.xokken.Servlets;

import ru.itis.xokken.Entity.Person;
import ru.itis.xokken.Repositories.PersonsRepImp;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.Services.PersonServiceImpl;
import ru.itis.xokken.dto.PersonDTO;
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

@WebServlet("/gates/home/girls")
public class ServletPersons extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDTO personDTO = PersonDTO.builder()
                .name(request.getParameter("personName"))
                .world(request.getParameter("personWorld"))
                .img(request.getParameter("personImg"))
                .build();
        String servant = request.getParameter("hero");
        if (servant == null){
            servant = "a";
        }
        PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
        if (servant.equals("false")){
            personService.deletePersonUsers((UserDTO) request.getSession().getAttribute("user"), personDTO);
        }
        else if (servant.equals("true")){
            personService.addPersonUsers((UserDTO) request.getSession().getAttribute("user"), personDTO);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        if (request.getParameter("worldImg") != null){
            session.setAttribute("wrldNm", request.getParameter("worldName"));
            session.setAttribute("wrldImg", request.getParameter("worldImg"));
        }
        WorldDTO worldDTO = WorldDTO.builder()
                .img((String) session.getAttribute("wrldImg"))
                .name((String) session.getAttribute("wrldNm"))
                .build();
        PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
        List<PersonDTO> personDTOS = personService.showAllPersonByWorld(worldDTO);
        request.setAttribute("personList", personDTOS);
        request.getRequestDispatcher("/JSPResources/GirlsPage.jsp").forward(request, response);

    }
}

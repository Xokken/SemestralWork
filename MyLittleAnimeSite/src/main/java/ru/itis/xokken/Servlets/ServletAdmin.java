package ru.itis.xokken.Servlets;

import ru.itis.xokken.Repositories.PersonsRepImp;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.Services.PersonServiceImpl;
import ru.itis.xokken.dto.PersonDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class ServletAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String add = request.getParameter("add");
        String delete = request.getParameter("delete");
        PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
        if (add != null){
            PersonDTO personDTO = PersonDTO.builder()
                    .name(request.getParameter("nameAdd"))
                    .world(request.getParameter("worldAdd"))
                    .img("/personImg/" + request.getParameter("imgAdd"))
                    .build();
            personService.save(personDTO);
        }
        if (delete != null){
            PersonDTO personDTO = PersonDTO.builder()
                    .name(request.getParameter("personNameDel"))
                    .world(request.getParameter("personWorldDel"))
                    .img(request.getParameter("personImgDel"))
                    .build();
            System.out.println(personDTO);
            personService.delete(personDTO);
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonServiceImpl personService = new PersonServiceImpl(new PersonsRepImp(), new UserRepImp());
        List<PersonDTO> personDTOS = personService.findAll();
        request.getSession().setAttribute("personListAdmin", personDTOS);
        request.getRequestDispatcher("/JSPResources/AdminPage.jsp").forward(request, response);
    }
}

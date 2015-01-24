package pl.krzysiek.servlets;

import pl.krzysiek.dao.AUserDAO;
import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.model.AUser;
import pl.krzysiek.model.Osoba;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/contacts", name = "ContactsServlet")
public class ContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OsobaDAO osobaDAO = new OsobaDAO();


        String operation = request.getParameter("operation");
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println(operation + "  " + id);

        if(operation.equals("del")){
            System.out.println("DELETE");
            osobaDAO.deleteOsoba(id);
        }
//        if(operation.equals("post")){
//            Osoba osoba = new Osoba();
//            osoba.setImie("z");
//            osoba.setOsobaId("zx");
//            osobaDAO.addOsoba(osoba);
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AUserDAO aUserDAO = new AUserDAO();

        HttpSession session = request.getSession();
        AUser aUser = (AUser) session.getAttribute("user");
        aUser.setContacts(aUserDAO.getAUserContactsList(aUser.getUserId()));

        request.setAttribute("contacts", aUser.getContacts());
        request.getRequestDispatcher("view/contacts.jsp").forward(request, response);
    }
}

package pl.krzysiek.servlets.Contacts;

import pl.krzysiek.dao.OsobaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/editContacts", name = "EditContactServlet")
public class EditContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OsobaDAO osobaDAO = new OsobaDAO();
        int id = Integer.parseInt(request.getParameter("id"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        System.out.println("I AM IN SERVLET " + contactId);

        request.setAttribute("wwws", "TESTING");

        request.getRequestDispatcher("view/editContact.jsp").forward(request, response);
    }
}

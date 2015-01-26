package pl.krzysiek.servlets.Contacts;

import org.json.JSONObject;
import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.service.ContactService;

import javax.servlet.RequestDispatcher;
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
        System.out.println( " INSIDE POST");
        int id = Integer.parseInt(request.getParameter("contactId"));
        System.out.println("GOT " + id);
        ContactService contactService = new ContactService();
        boolean canBeUpdated =  contactService.updateUser(request, id);

        JSONObject json = null;
        response.setContentType("application/json");
        try {
            json = new JSONObject();
            json.put("updated", canBeUpdated);
        }catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        response.getWriter().print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        String imie = request.getParameter("imie");
        String nazwisko = request.getParameter("nazwisko");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String telefon = request.getParameter("telefon");

        request.setAttribute("imie", imie);
        request.setAttribute("nazwisko", nazwisko);
        request.setAttribute("dob", dob);
        request.setAttribute("email", email);
        request.setAttribute("telefon", telefon);
        request.setAttribute("contactId", contactId);

        request.getRequestDispatcher("view/editContact.jsp").forward(request, response);
    }
}

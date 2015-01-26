package pl.krzysiek.servlets.Contacts;

import org.json.JSONObject;
import pl.krzysiek.dao.AUserDAO;
import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.model.AUser;
import pl.krzysiek.model.Osoba;
import pl.krzysiek.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet(value = "/contacts", name = "ContactsServlet")
public class ContactsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ContactService contactService = new ContactService();
        AUser aUser = (AUser) request.getSession().getAttribute("user");
        boolean emailExists;
        int success = 0;

        if(!contactService.checkForEmail(aUser.getUserId(), request)){
            success = contactService.addUser(request, aUser.getUserId());
            emailExists = false;
        }else{
            emailExists = true;
        }

        JSONObject json = null;
        response.setContentType("application/json");
        try {
            json = new JSONObject();
            json.put("emailExists", emailExists);
            json.put("succ", success);
            json.put("contactID", success);
        }catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        response.getWriter().print(json);

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

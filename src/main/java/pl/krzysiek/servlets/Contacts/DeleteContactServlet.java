package pl.krzysiek.servlets.Contacts;

import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.model.AUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteContacts", name = "DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OsobaDAO osobaDAO = new OsobaDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        AUser a = (AUser) request.getSession().getAttribute("user");
            osobaDAO.deleteOsoba(id, a.getUserId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

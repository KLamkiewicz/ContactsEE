package pl.krzysiek.servlets;

import pl.krzysiek.dao.OsobaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by krzysiek on 23.01.15.
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OsobaDAO osobaDAO = new OsobaDAO();

        request.setAttribute("osoby", osobaDAO.getAllOsoba());
        request.getRequestDispatcher("view/users.jsp").forward(request, response);
    }
}

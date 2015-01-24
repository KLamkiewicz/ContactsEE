package pl.krzysiek.servlets;

import pl.krzysiek.dao.OsobaDAO;
import pl.krzysiek.model.Osoba;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OsobaDAO o = new OsobaDAO();
        List<Osoba> osoby = o.getAllOsoba();
        request.setAttribute("contacts", osoby);
        request.getRequestDispatcher("view/index.jsp").forward(request, response);
    }


}

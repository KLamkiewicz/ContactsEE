package pl.krzysiek.servlets;



import org.json.JSONObject;
import pl.krzysiek.dao.AUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by krzysiek on 23.01.15.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Log " + login + " " + password);

        AUserDAO aUserDAO = new AUserDAO();
        boolean exists = aUserDAO.userExistsAlready(login);
        System.out.println(exists);
        JSONObject json = null;
        response.setContentType("application/json");
        try {
            json = new JSONObject();
            json.put("exists", exists);
        }catch (Exception e){
            System.out.println("ERROR " + e);
        }
        response.getWriter().print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
    }
}

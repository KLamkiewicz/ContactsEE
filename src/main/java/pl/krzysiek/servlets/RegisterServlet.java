package pl.krzysiek.servlets;

import org.json.JSONObject;
import pl.krzysiek.dao.AUserDAO;
import pl.krzysiek.model.AUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by krzysiek on 23.01.15.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginB = (login.length()>=3 && login.length()<=20)?true:false;
        boolean passwordB = (password.length()>=5 && password.length()<=15)?true:false;

        AUserDAO aUserDAO = new AUserDAO();
        boolean exists = aUserDAO.userExistsAlready(login);
        JSONObject json = null;
        response.setContentType("application/json");
        try {
            json = new JSONObject();
            json.put("loginl", loginB);
            json.put("passl", passwordB);
            json.put("exists", exists);
        }catch (Exception e){
            System.out.println("ERROR " + e);
        }

        if(!exists && loginB && passwordB) {
            aUserDAO.createUser(new AUser(login, password));
        }
        response.getWriter().print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
    }
}

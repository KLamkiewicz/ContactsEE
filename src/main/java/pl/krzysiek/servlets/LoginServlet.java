package pl.krzysiek.servlets;



import org.json.JSONObject;
import pl.krzysiek.dao.AUserDAO;
import pl.krzysiek.model.AUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        boolean loginB = (login.length()>=3 && login.length()<=20)?true:false;
        boolean passwordB = (password.length()>=5 && password.length()<=15)?true:false;
        boolean authenticated = false;
        AUserDAO aUserDAO = new AUserDAO();

        if(loginB && passwordB) {
            AUser aUser = aUserDAO.getUser(login, password);
            if(aUser!=null){
                authenticated = true;
                HttpSession session = request.getSession();
                session.setAttribute("user", aUser);
                session.setMaxInactiveInterval(60*60);
            }
        }

        JSONObject json = null;
        response.setContentType("application/json");
        try {
            json = new JSONObject();
            json.put("authenticated", authenticated);
        }catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        response.getWriter().print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
    }

}

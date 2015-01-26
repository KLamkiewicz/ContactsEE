package pl.krzysiek.filters;

import pl.krzysiek.model.AUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by krzysiek on 24.01.15.
 */
@WebFilter(value = "/contacts", filterName = "ContactsFilter")
public class ContactsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession();
        AUser aUser = (AUser) session.getAttribute("user");

        if(aUser==null){
            httpResponse.sendRedirect("/");
            return;
        }

        //if post not mine

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

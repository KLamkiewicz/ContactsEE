package pl.krzysiek.filters;

import pl.krzysiek.model.AUser;
import pl.krzysiek.service.ContactService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by krzysiek on 25.01.15.
 */
@WebFilter(value="/editContacts", filterName = "EditcontactsFilter")
public class EditcontactsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpreq = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        AUser aUser = (AUser) httpreq.getSession().getAttribute("user");
        ContactService contactService = new ContactService();
        boolean belong = false;
        System.out.println("INSIDE FILTER");
        System.out.println(req.getParameter("contactId"));

        try{
            belong = contactService.contactBelongsToUser(aUser.getUserId(), Integer.parseInt(req.getParameter("contactId")));
        }catch (Exception e){
            httpResponse.sendRedirect("/");
        }

        if(!belong){
            httpResponse.sendRedirect("/");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

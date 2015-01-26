package pl.krzysiek.filters;

import pl.krzysiek.model.AUser;
import pl.krzysiek.service.ContactService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by krzysiek on 26.01.15.
 */
@WebFilter(value = "/deleteContacts", filterName = "DeleteContactsFilter")
public class DeleteContactsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpreq = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        AUser aUser = (AUser) httpreq.getSession().getAttribute("user");
        ContactService contactService = new ContactService();
        boolean belong = false;
        int contactId = Integer.parseInt(httpreq.getParameter("id"));
        try {
            belong = contactService.contactBelongsToUser(aUser.getUserId(), contactId);
        }catch (Exception e){
            httpResponse.sendRedirect("/contacts");
        }
        if(!belong){
            httpResponse.sendRedirect("/contacts");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

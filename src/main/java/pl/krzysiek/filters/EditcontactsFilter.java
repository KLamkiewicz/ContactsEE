package pl.krzysiek.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by krzysiek on 25.01.15.
 */
@WebFilter(filterName = "EditcontactsFilter")
public class EditcontactsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

       //Sprawdzic moge edytowac czy nie ownerId w przekazanym osobaId
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

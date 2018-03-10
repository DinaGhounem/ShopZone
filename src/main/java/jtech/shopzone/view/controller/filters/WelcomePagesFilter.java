package jtech.shopzone.view.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "WelcomePagesFilter")
public class WelcomePagesFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession httpSession = httpRequest.getSession();

        if(httpSession!=null)
        {
            Boolean isLogged = (Boolean) httpSession.getAttribute("loggedIn");
            if(isLogged!=null && isLogged)
            {
                Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");
                if(isAdmin!=null && isAdmin)
                {
                    httpServletResponse.sendRedirect("/adminHome.jsp");
                }
                else
                {
                    httpServletResponse.sendRedirect("/home.jsp");
                }
            }
            else
            {
                    chain.doFilter(req, resp);
            }

        }
        else
        {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

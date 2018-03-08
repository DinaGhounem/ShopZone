package jtech.shopzone.view.controller.filters;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mahrous
 */
    @WebFilter(filterName = "SessionCheckerFilter")
public class SessionCheckerFilter implements Filter {
    private ArrayList<String> adminPages = new ArrayList<String>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;

        String requestUrl = httpRequest.getRequestURL().toString();

        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpSession httpSession = httpRequest.getSession();
        if (httpSession != null) {
            Boolean isLogged = (Boolean) httpSession.getAttribute("loggedIn");
            Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");

            if (isLogged != null && isLogged) {
                // Check destination
                boolean adminDest = true;

                // if destination is admin pages ensure that session
                // has isAdmin attr
                if (adminDest) {
                    if (isAdmin != null && isAdmin) {
                    chain.doFilter(req,resp);
                    } else {
                        httpServletResponse.sendRedirect("/signin.html");
                    }
                } else {
                    // this is user destination
                    chain.doFilter(req,resp);
                }

            } else {
                httpServletResponse.sendRedirect("/signin.html");
            }

        } else {
            httpServletResponse.sendRedirect("/signin.html");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

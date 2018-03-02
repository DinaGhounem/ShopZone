package jtech.shopzone.view.controller.filters;

import jtech.shopzone.controller.CartController;
import jtech.shopzone.controller.impl.CartControllerImpl;
import jtech.shopzone.model.entity.CartEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "CartFilter", urlPatterns = "/cart.jsp")
public class CartFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // TODO: Get id from session
        int userId = 10;
        CartController cartController = CartControllerImpl.newInstance();
        ArrayList<CartEntity> cartEntities =  cartController.getUserProducts(userId);
        req.setAttribute("cartEntities",cartEntities);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

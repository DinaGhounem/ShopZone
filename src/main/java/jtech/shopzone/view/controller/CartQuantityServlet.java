package jtech.shopzone.view.controller;

import jtech.shopzone.controller.CartController;
import jtech.shopzone.controller.impl.CartControllerImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Muhammed Mahrous
 */
@WebServlet(name = "CartQuantityServlet", urlPatterns = "/CartQuantityServlet")
public class CartQuantityServlet extends HttpServlet {
    
    private CartController cartController = CartControllerImpl.newInstance();
    ;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        if (httpSession != null) {
            Integer userId = (Integer) httpSession.getAttribute("userId");
            int itemCount = 0;
            //cartController.userItemCount(userId);
            if (itemCount != -1) {
                response.getWriter().write(String.valueOf(itemCount));
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

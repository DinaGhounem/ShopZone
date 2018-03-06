package jtech.shopzone.view.controller;

import jtech.shopzone.controller.CartController;
import jtech.shopzone.controller.impl.CartControllerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mohamed Mahrous
 */
@WebServlet(name = "CheckOut")
public class CheckOut extends HttpServlet {
    private CartController cartController;
    @Override
    public void init() throws ServletException {
        super.init();
        cartController = CartControllerImpl.newInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: get user id from session
        int userId = 1;
        cartController.get

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

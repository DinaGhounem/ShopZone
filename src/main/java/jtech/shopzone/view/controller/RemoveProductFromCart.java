/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import jtech.shopzone.controller.CartController;
import jtech.shopzone.controller.impl.CartControllerImpl;
import jtech.shopzone.model.dal.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Mahrous
 */
@WebServlet(name = "RemoveProductFromCart", urlPatterns = {"/RemoveProductFromCart"})
public class RemoveProductFromCart extends HttpServlet {

    private CartController cartController;

    @Override
    public void init() throws ServletException {
        super.init();
        cartController = CartControllerImpl.newInstance();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO : get userId from session
        int userId = 1;
        int productId = Integer.parseInt(request.getParameter("productId"));
        String remove = request.getParameter("removeProduct");
        if (remove == null) {
            if (cartController.checkProductExistance(userId, productId) == Status.NOTOK) {
                cartController.addProduct(userId, productId);
            } else {
                int quantity = cartController.getQuantity(userId, productId) - 1;
                cartController.updateProductQuantities(userId, productId, quantity);
            }
        } else {
            cartController.deleteProduct(userId,productId);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jtech.shopzone.controller.CartController;
import jtech.shopzone.controller.ProductController;
import jtech.shopzone.controller.impl.CartControllerImpl;
import jtech.shopzone.controller.impl.ProductControllerImpl;
import jtech.shopzone.model.dal.Status;

/**
 *
 * @author Hanaa
 */
@WebServlet(name = "AddProductToCart", urlPatterns = {"/AddProductToCart"})
public class AddProductToCart extends HttpServlet {

    private CartController cartController;
    private ProductController productController;

    @Override
    public void init() throws ServletException {
        super.init();
        cartController = CartControllerImpl.newInstance();
        productController = ProductControllerImpl.newInstance();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductToCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        PrintWriter out = response.getWriter();

        int productId = Integer.parseInt(request.getParameter("productId"));
        int totalQuantities = productController.checkProductQuantities(productId);
        if (totalQuantities > 0) {
            int userId = 1;//TODO get id from login session
            if (cartController.checkProductExistance(userId, productId) == Status.NOTOK) {
                cartController.addProduct(userId, productId);
            } else {
                int quantity = cartController.getQuantity(userId, productId) + 1;
                cartController.updateProductQuantities(userId, productId, quantity);
            }
            productController.updateProductQuantities(productId, totalQuantities - 1);
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

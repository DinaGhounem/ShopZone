
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jtech.shopzone.controller.ProductController;
import jtech.shopzone.controller.impl.ProductControllerImpl;
import jtech.shopzone.model.dal.dao.ProductDao;
import jtech.shopzone.model.dal.dao.impl.ProductDaoImpl;
import jtech.shopzone.model.entity.ProductsInfoEntity;

/**
 *
 * @author Hanaa
 */
@WebServlet(name = "ShowProductServlet", urlPatterns = {"/ShowProductServlet"})
public class ShowProductServlet extends HttpServlet {

    private ProductController productController;

    @Override
    public void init() throws ServletException {
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
            out.println("<title>Servlet ShowProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowProductServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        String page = request.getParameter("page");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        if (page != null) {

            int pageNum = Integer.parseInt(page);
            ArrayList<ProductsInfoEntity> products = new ArrayList<>();
            if (categoryId == 0 && to == null && from == null) {

                products = productController.getProductsBTWRange(pageNum);
            } else if (to != null && from != null) {

                products = productController.getProductsBTWRange(pageNum, Double.parseDouble(from), Double.parseDouble(to));

            } else {

                products = productController.getProductsBTWRange(pageNum, categoryId);
            }
            Gson gson = new Gson();
            String jsonObject = gson.toJson(products);
            out.print(jsonObject);

        } else {
            String flag = request.getParameter("flag");
            int productCount = 0;
            if (flag == null) {
                if (categoryId == 0) {
                    productCount = productController.getProductCount();
                } else {

                    productCount = productController.getProducts(categoryId).size();
                }
            } else {

                productCount = productController.getProducts(Double.parseDouble(from), Double.parseDouble(to)).size();

            }

            out.print(productCount);
        }
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
        processRequest(request, response);
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

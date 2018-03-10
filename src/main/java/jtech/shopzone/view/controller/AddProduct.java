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
import jtech.shopzone.controller.ProductController;
import jtech.shopzone.controller.impl.ProductControllerImpl;
import jtech.shopzone.model.entity.ProductsInfoEntity;
import jtech.shopzone.view.util.FileUploadAdapter;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Hanaa
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

    ProductController productController = null;

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        FileUploadAdapter adapter = new FileUploadAdapter(request);
         PrintWriter out = response.getWriter();
     
        String name = adapter.getParameter("name");
        String price = adapter.getParameter("price");
        String quantity = adapter.getParameter("quantity");
        String description = adapter.getParameter("description");
        FileItem imageFileItem = adapter.getFile("file_img");
        if (imageFileItem!=null) {
            String img = imageFileItem.getName();
        adapter.writeToFolder(img, "/img", getServletContext(), imageFileItem);
        String category = adapter.getParameter("categoryId");
        if (name != null && price != null && quantity != null && description != null && img != null && category != null) {
            ProductsInfoEntity product = new ProductsInfoEntity(0, name, Double.parseDouble(price), Integer.parseInt(quantity), description, Integer.parseInt(category), "img/" + img);
            productController.AddProduct(product);
            response.sendRedirect("adminHome.jsp");
        } else {
            out.print(category);

        }
        }
        
        /*String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String description = request.getParameter("description");
        String img = request.getParameter("file_img");
        String category = request.getParameter("categoryId");
        if (name != null && price != null && quantity != null && description != null && img != null && category != null) {
            ProductsInfoEntity product = new ProductsInfoEntity(0, name, Double.parseDouble(price), Integer.parseInt(quantity), description, Integer.parseInt(category), "img/" + img);
            productController.AddProduct(product);
        } else {
            out.print(category);

        }*/
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

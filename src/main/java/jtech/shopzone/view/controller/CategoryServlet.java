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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jtech.shopzone.controller.CategoryController;
import jtech.shopzone.controller.impl.CategoryControllerImpl;
import jtech.shopzone.model.entity.ProductCategoryEntity;

/**
 *
 * @author Dina PC
 */
public class CategoryServlet extends HttpServlet {

    CategoryController catController = CategoryControllerImpl.newInstance();;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ArrayList<ProductCategoryEntity> categories =  catController.getCategories();
       Gson gson= new Gson();
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(categories));
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}

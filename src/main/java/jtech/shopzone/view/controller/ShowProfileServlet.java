/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jtech.shopzone.controller.UserController;
import jtech.shopzone.controller.impl.UserControllerImpl;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author Dina PC
 */
public class ShowProfileServlet extends HttpServlet {
  
    UserController ucontroller;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /**
         * get user id from the session
         */
        int userId = 1; //(int) request.getSession().getAttribute("userId");
        
        /**
         * get user info from db
         */
        ucontroller = UserControllerImpl.newInstance();
        
        UserInfoEntity user =  ucontroller.getUserInfo(userId);
        
        /**
         * convert user to json and send to jsp page
         */
        Gson gson= new Gson();
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(user));
    }

}

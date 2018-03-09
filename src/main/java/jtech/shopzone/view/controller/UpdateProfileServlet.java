/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jtech.shopzone.controller.UserController;
import jtech.shopzone.controller.impl.UserControllerImpl;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author Dina PC
 */
public class UpdateProfileServlet extends HttpServlet {

    UserController ucontroller;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * get user id from the session
         */
        int userId = (int) request.getSession().getAttribute("userId");
        
        /**
         * get user info from db
         */
        ucontroller = UserControllerImpl.newInstance();
        
        UserInfoEntity user =  ucontroller.getUserInfo(userId);
       
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String pass = request.getParameter("password");
        String address = request.getParameter("address");
        String job = request.getParameter("job");
        String  creditLimit = request.getParameter("credit");
        
        //String img = request.getParameter("img");
        
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPassword(pass);
        user.setAddress(address);
        user.setJob(job);
        user.setCreditLimit(Double.parseDouble(creditLimit));
        
        Status states = ucontroller.updateUser(user);
        
        if(states == Status.OK)
            response.sendRedirect("home.jsp");
        else
            response.sendRedirect("UserProfile.jsp");
        
    }

}

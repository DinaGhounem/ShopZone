/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jtech.shopzone.controller.UserController;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author sulta
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})

public class LoginServlet extends HttpServlet {

    private UserController userController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/signin.html");
        } else {
            String loggedIn = (String) session.getAttribute("loggedIn");
            if (!loggedIn.equalsIgnoreCase("true")) {
                response.sendRedirect("signin.html");
                
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //---------------------initialization--------------------------------//

        Status loginAck;
        //---------------------response--------------------------------//
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //-----------------------login user in db--------------------//        
        loginAck = userController.login(request.getParameter("email"), request.getParameter("password"));
        //-----------------------create httpsession--------------------//
        if (loginAck == Status.OK) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", new String("true"));
            session.setAttribute("userEmail", request.getParameter("email"));//or give me in return user id to put it on the session
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
        } else if (loginAck == Status.NOTOK) {
            out.print(" this email is not exists !");
            RequestDispatcher rd = request.getRequestDispatcher("/signin.html");
            rd.include(request, response);

        } else if (loginAck == Status.ERROR) {
            out.print(" Sorry Error in connection Try again later !");
            RequestDispatcher rd = request.getRequestDispatcher("/signin.html");
            rd.include(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

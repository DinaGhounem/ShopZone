/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jtech.shopzone.controller.UserController;
import jtech.shopzone.controller.impl.UserControllerImpl;
import jtech.shopzone.controller.impl.UserControllerImpl;
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
    public void init() throws ServletException {
        userController=UserControllerImpl.newInstance();
    }

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
        Status adminAck;
        boolean isAdmin = false;

        //---------------------response--------------------------------//
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //----------------------if admin-------------------------------//
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        adminAck = userController.isAdmin(email,password);
        if (adminAck == Status.OK) {
            isAdmin = true;
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", new Boolean(true));
            session.setAttribute("isAdmin", new Boolean(true));
            session.setAttribute("adminEmail", request.getParameter("email"));//or give me in return user id to put it on the session
            session.setAttribute("adminId", userController.getAdminId(request.getParameter("email")));
            RequestDispatcher rd = request.getRequestDispatcher("/adminpage.jsp");
            rd.forward(request, response);

        } else if (adminAck == Status.NOTOK) {

            isAdmin = false;

        } else if (adminAck == Status.ERROR) {
            isAdmin = false;
        }

        //-----------------------login user in db--------------------//        
        if (!isAdmin) {
            loginAck = userController.login(request.getParameter("email"), request.getParameter("password"));

            //-----------------------create httpsession--------------------//
            if (loginAck == Status.OK) {
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn", new Boolean(true));
                 session.setAttribute("isAdmin", new Boolean(false));
                session.setAttribute("userEmail", request.getParameter("email"));//or give me in return user id to put it on the session
                session.setAttribute("userId", userController.getUserId(request.getParameter("email")));
                RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
                rd.forward(request, response);
            } else if (loginAck == Status.NOTOK) {
                response.sendRedirect("signin.html?Status=notok&errormessage=Wrong email or password");
            } else if (loginAck == Status.ERROR) {

                response.sendRedirect("signin.html?Status=error&errormessage=Sorry Error-in-connection-Try-again-later");
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

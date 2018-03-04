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
import jtech.shopzone.model.dal.util.CustomDate;
import jtech.shopzone.model.entity.UserInfoEntity;

/**
 *
 * @author sulta
 */
@WebServlet(name = "RegisterationServlet", urlPatterns = {"/RegisterationServlet"})

public class RegisterationServlet extends HttpServlet {

    private UserController userController;
    private static final String dateFormat = new String("mm/dd/yyyy");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("singin.html");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserInfoEntity newMember = new UserInfoEntity();
        Status registerAck;
        //---------------------response--------------------------------//
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //----------------------getparameter from registeration form ---//
        newMember.setFirstName(request.getParameter("firstName"));
        newMember.setLastName(request.getParameter("lastName"));
        newMember.setEmail(request.getParameter("email"));
        newMember.setPassword(request.getParameter("password"));
        newMember.setJob(request.getParameter("job"));
        newMember.setAddress(request.getParameter("address"));
        newMember.setCreditLimit(Double.parseDouble(request.getParameter("creditLimit")));
        //   newMember.setBirthdate(new CustomDate(request.getParameter("date"),dateFormat));// i need modification in database model
        newMember.setUserImg("/signincludes/images/img-01.png");//fixed image
        //-----------------------validate email regex ------------------//

        //-----------------------register user in db--------------------//
        registerAck = userController.register(newMember);
        //-----------------------create httpsession--------------------//
        if (registerAck == Status.OK) {

            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", new String("true"));
            session.setAttribute("userEmail", newMember.getEmail());//change this with userid modif
            session.setAttribute("userID", newMember.getUserId());
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);

        } else if (registerAck == Status.NOTOK) {
            out.print(" this email is already exsist !");
            RequestDispatcher rd = request.getRequestDispatcher("/signin.html");
            rd.include(request, response);
            
        } else if (registerAck == Status.ERROR) {
            out.print(" Sorry Error in connection Try again later !");
            RequestDispatcher rd = request.getRequestDispatcher("/signup.html");
            rd.include(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

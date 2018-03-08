/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtech.shopzone.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jtech.shopzone.controller.UserController;
import jtech.shopzone.controller.impl.UserControllerImpl;
import jtech.shopzone.model.dal.Status;
import jtech.shopzone.model.dal.util.CustomDate;
import jtech.shopzone.model.entity.AdminInfoEntity;
import jtech.shopzone.model.entity.UserInfoEntity;
import jtech.shopzone.model.entity.UserInterestsEntity;

/**
 *
 * @author sulta
 */
@WebServlet(name = "RegisterationServlet", urlPatterns = {"/RegisterationServlet"})

public class RegisterationServlet extends HttpServlet {

    private UserController userController;
    private static final String dateFormat = new String("mm/dd/yyyy");

    @Override
    public void init() throws ServletException {
        userController = UserControllerImpl.newInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signin.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserInfoEntity newMember = new UserInfoEntity();
        ArrayList<UserInterestsEntity> memberInterests = new ArrayList<UserInterestsEntity>();
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
        String[] checkedInterests = request.getParameterValues("interest");
        if ( checkedInterests!=null &&checkedInterests.length > 0) {
            for (String checkedInterest : checkedInterests) {

                memberInterests.add(new UserInterestsEntity(0, checkedInterest));
            }
        }
        newMember.setInterests(memberInterests);
        //-------------------------------mahrous part--------------------------------------//  

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String birthdate = request.getParameter("birthDate");            
            if(birthdate!=null){
            Date parsed = format.parse(birthdate);
            newMember.setBirthdate(parsed);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("/signin.html");
        }

        //-----------------------------------------------------------------------------------
        newMember.setUserImg("/signincludes/images/user.png");//fixed image
        //-----------------------validate email regex ------------------//

        //-----------------------register user in db--------------------//
        Status checkEmail = userController.checkEmail(newMember.getEmail());
        if (checkEmail == Status.OK) {
            registerAck = userController.register(newMember);
            //-----------------------create httpsession--------------------//
            if (registerAck == Status.OK) {
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn", new String("true"));
                session.setAttribute("userEmail", newMember.getEmail());//
                session.setAttribute("userId", userController.getUserId(newMember.getEmail()));
                RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
                rd.forward(request, response);

            } else if (registerAck == Status.NOTOK) {
                HttpSession session = request.getSession(false);
                if (session == null) {
                    response.sendRedirect("/signin.html");
                } else {
                    String isAdmin = (String) session.getAttribute("isAdmin");
                    String loggedIn = (String) session.getAttribute("loggedIn");
                    if (!isAdmin.equalsIgnoreCase("true")) {//not admin
                        if (!loggedIn.equalsIgnoreCase("true")) {
                            response.sendRedirect("signin.html");

                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
                            rd.forward(request, response);
                        }

                    } else {//admin
                        response.sendRedirect("/adminpage.jsp");
                    }
                }
            } else if (registerAck == Status.ERROR) {
                response.sendRedirect("signup.html?Status=error&errormessage=Sorry-Error-in-connection-Try-again-later");
            }
        } else {
            response.sendRedirect("signup.html?Status=error&errormessage=Wrong email or password");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
// out.print(" Sorry Error in connection Try again later !");
//RequestDispatcher rd = request.getRequestDispatcher("/signup.html");
            //rd.include(request, response);

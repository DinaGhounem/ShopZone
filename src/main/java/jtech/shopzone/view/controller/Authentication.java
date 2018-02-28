package jtech.shopzone.view.controller;

import jtech.shopzone.controller.UserController;
import jtech.shopzone.controller.impl.UserControllerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * THIS IS JUST AN EXAMPLE CLASS ON HOW
 * 1. Validator will be used.
 * 2. controller class will be used.
 */
@WebServlet(name = "Authentication")
public class Authentication extends HttpServlet {
    private UserController userController;

    @Override
    public void init() throws ServletException {
        super.init();
        userController = UserControllerImpl.newInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*   EXAMPLE USECASE FOR LOGIN */
        /*
        1. Validate using a method in the validator class,
        including checking if request has null parameters
        2. if validation is successful then continue
            3. call login function in usercontroller
            userController.login(request.getParameter("username"),request.getParameter("password"));
            4. redirect user to homepage
        3. if validation fails then redirect to login again maybe with appropriate message
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

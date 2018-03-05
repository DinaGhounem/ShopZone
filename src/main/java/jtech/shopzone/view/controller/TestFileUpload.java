package jtech.shopzone.view.controller;

import jtech.shopzone.view.util.FileUploadAdapter;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "TestFileUpload" , urlPatterns = {"/TestFileUpload"})
public class TestFileUpload extends HttpServlet {
    private ServletContext servletContext;
    @Override
    public void init() throws ServletException {
        super.init();
       servletContext = getServletContext();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileUploadAdapter fileUploadAdapter = new FileUploadAdapter(request);

        FileItem item = fileUploadAdapter.getFile("profilePic");
        fileUploadAdapter.writeToFolder(item.getName(),"/img",servletContext,item);
        response.sendRedirect("/img/"+item.getName());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

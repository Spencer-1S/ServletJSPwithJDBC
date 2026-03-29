package com.spencer.servletjspwithjdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "getFormData", value = "/FData")
public class FData extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        response.getWriter().println("<h1>Name: " + name + "</h1>");
        response.getWriter().println("<h1>Email: " + email + "</h1>");
        response.getWriter().println("<h1>Password: " + password + "</h1>");

    }
}

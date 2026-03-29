package com.spencer.servletjspwithjdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


// This servlet is not following the MVC architecture

@WebServlet(name = "getFormData", value = "/FData")
public class FData extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        String dbUrl = "jdbc:mysql://127.0.0.1:3306/servletjspwithjdbc";
        String username = "root";
        String password1 = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, username, password1);
            System.out.println("Connected to database successfully!");

            // email should be unique
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO users (name, email, password) VALUES (?, ?, ?)"
            );
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            con.close();
            if( rowsAffected > 0)
                response.getWriter().printf("Inserted %d row(s) into the users table.%n", rowsAffected);
            else
                response.getWriter().println("No rows were affected. Something went wrong.");

        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }



//        response.setContentType("text/html");
//        response.getWriter().println("<h1>Name: " + name + "</h1>");
//        response.getWriter().println("<h1>Email: " + email + "</h1>");
//        response.getWriter().println("<h1>Password: " + password + "</h1>");

    }
}

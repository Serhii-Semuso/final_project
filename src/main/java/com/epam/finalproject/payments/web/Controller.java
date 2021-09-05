package com.epam.finalproject.payments.web;

import com.epam.finalproject.payments.db.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 687955506364681924L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        DBManager db = DBManager.getInstance();
        pw.println("CONTROLLER");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.epam.finalproject.payments.web;

import com.epam.finalproject.payments.web.command.Command;
import com.epam.finalproject.payments.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 687955506364681924L;

    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = process(request, response);
        log.trace("Controller finished, now go to forward address --> " + address);
        if (address != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = process(request, response);
        log.trace("Controller finished, now go to redirect address --> " + address);
        if (address != null) {
           response.sendRedirect(address);
        }
    }

    private String process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        log.trace("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.info("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        log.info("Obtained command --> " + command);

        // execute command and get address
        String address = command.execute(request, response);
        log.trace("Forward address --> " + address);

        return address;
    }

}

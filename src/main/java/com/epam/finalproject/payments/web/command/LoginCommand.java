package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.dao.imp.UserDaoImp;
import com.epam.finalproject.payments.db.entity.Role;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        log.debug("Command starts");

        // obtain login and password from the request
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // error handler
        String errorMessage;
        String address = Path.PAGE_ERROR;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return address;
        }

        User user = new UserDaoImp().findByLogin(login);
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return address;
        } else {
            Role userRole = Role.getRole(user);
            log.trace("userRole --> " + userRole);
            address = Path.COMMAND_USER_MAIN;
            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);
            session.setAttribute("userRole", userRole);
            log.trace("Set the session attribute: userRole --> " + userRole);
            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
        }

        log.debug("Command finished");

        return address;
    }
}

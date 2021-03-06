package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewRegisterCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewRegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        String address = Path.PAGE_REGISTRATION;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            address = Path.COMMAND_USER_MAIN;
        }
        log.debug("Command finished");
        return address;
    }

}

package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ViewOpenAccountCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewOpenAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        String address = Path.PAGE_LOGIN;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            address = Path.PAGE_OPEN_ACCOUNT;
        }
        log.debug("Command finished");
        return address;
    }
}

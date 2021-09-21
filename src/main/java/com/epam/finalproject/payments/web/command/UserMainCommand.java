package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserMainCommand implements Command {

    private static final Logger log = Logger.getLogger(UserMainCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        log.debug("Command finished");
        return Path.PAGE_USER_MAIN;
    }
}

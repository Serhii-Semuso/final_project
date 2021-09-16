package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewRegisterCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewRegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        log.debug("Command finished");
        return Path.PAGE_REGISTRATION;
    }

}

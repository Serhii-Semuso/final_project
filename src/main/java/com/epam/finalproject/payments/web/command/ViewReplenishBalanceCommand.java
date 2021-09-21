package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ViewReplenishBalanceCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewReplenishBalanceCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");

        String address = Path.PAGE_LOGIN;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.debug("No user in session. Command finished");
            return address;
        }

        log.debug("Obtained account id: " + request.getParameter("accountId"));
        address = Path.PAGE_REPLENISH_BALANCE;

        log.debug("Command finished");
        return address;
    }

}

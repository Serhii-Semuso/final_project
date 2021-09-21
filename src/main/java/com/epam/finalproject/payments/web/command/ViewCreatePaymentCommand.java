package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.dao.imp.AccountDaoImp;
import com.epam.finalproject.payments.db.entity.Account;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.stream.Collectors;

public class ViewCreatePaymentCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewCreatePaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        String address;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            address = Path.PAGE_LOGIN;
        } else {
            Collection<Account> userAccounts = new AccountDaoImp().findByUserId(user.getId());
            userAccounts = userAccounts.stream()
                    .filter(x -> !x.getBlocked())
                    .collect(Collectors.toList());
            request.setAttribute("userAccounts", userAccounts);

            Collection<Account> allAccounts = new AccountDaoImp().findAll();
            allAccounts = allAccounts.stream()
                    .filter(x -> !x.getBlocked())
                    .collect(Collectors.toList());
            request.setAttribute("allAccounts", allAccounts);

            address = Path.PAGE_CREATE_PAYMENT;
        }
        log.debug("Command finished");
        return address;
    }

}

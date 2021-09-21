package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.dao.abstraction.EntityDao;
import com.epam.finalproject.payments.db.dao.imp.AccountDaoImp;
import com.epam.finalproject.payments.db.entity.Account;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UnblockAccountRequestCommand implements Command {

    private static final Logger log = Logger.getLogger(UnblockAccountRequestCommand.class);

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

        Long accountId = Long.parseLong(request.getParameter("accountId"));
        log.debug("Account id = " + accountId);

        String errorMessage;
        address = Path.PAGE_ERROR;

        if (accountId == null) {
            errorMessage = "Account id cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return address;
        }

        EntityDao<Account> accountDao = new AccountDaoImp();
        Account account = accountDao.findById(accountId);

        if (account == null) {
            errorMessage = "Cannot find account";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return address;
        }
        log.debug("Found account = " + account);
        account.setUnblockRequest(true);
        log.debug("Account getUnblockRequest(): " + account.getUnblockRequest());
        accountDao.update(account);
        address = Path.COMMAND_VIEW_ACCOUNTS;
        log.debug("Command finished");
        return address;
    }
}
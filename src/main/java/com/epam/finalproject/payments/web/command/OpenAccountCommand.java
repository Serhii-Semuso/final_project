package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.dao.abstraction.EntityDao;
import com.epam.finalproject.payments.db.dao.imp.AccountDaoImp;
import com.epam.finalproject.payments.db.dao.imp.CreditCardDaoImp;
import com.epam.finalproject.payments.db.entity.Account;
import com.epam.finalproject.payments.db.entity.CreditCard;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class OpenAccountCommand implements Command {

    private static final Logger log = Logger.getLogger(OpenAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.warn("No user in session. Command finished");
            return Path.PAGE_LOGIN;
        }

        String name = request.getParameter("name");
        log.info("User with id:" + user.getId() + " start creating account with name:" + name);
        String errorMessage;
        if (name == null) {
            errorMessage = "Name cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_ERROR;
        }

        EntityDao<Account> accountDao = new AccountDaoImp();
        Account account = new Account();
        account.setName(name);
        account.setUserId(user.getId());

        accountDao.insert(account);
        Long accountId = account.getId();

        log.info("Account added with id: " + accountId);

        EntityDao<CreditCard> cardDao = new CreditCardDaoImp();
        CreditCard card = new CreditCard();
        card.setLimit(new BigDecimal(1000));
        card.setAccountId(account.getId());
        cardDao.insert(card);

        log.info("Credit card:" + card + " added to account with id: " + accountId);

        log.debug("Command finished");
        return Path.COMMAND_USER_MAIN;
    }
}

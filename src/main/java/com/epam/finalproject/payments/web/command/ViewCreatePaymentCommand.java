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

public class ViewCreatePaymentCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewCreatePaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user == null){
            address = Path.PAGE_LOGIN;
        }else {
            Collection<Account> accounts = new AccountDaoImp().findByUserId(user.getId());
            request.setAttribute("accounts", accounts);
            address = Path.PAGE_LIST_ACCOUNTS;
        }
        return address;
    }

}

package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.dao.imp.PaymentDaoImp;
import com.epam.finalproject.payments.db.entity.Payment;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ViewPaymentsCommand implements Command {

    private static final Logger log = Logger.getLogger(ViewPaymentsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");
        String address;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user == null){
            address = Path.PAGE_LOGIN;
        }else {
            Collection<Payment> payments = new PaymentDaoImp().findByUserId(user.getId());
            request.setAttribute("payments", payments);
            log.debug("Found payments: " + payments);
            address = Path.PAGE_LIST_PAYMENTS;
        }
        log.debug("Command finished");
        return address;
    }

}

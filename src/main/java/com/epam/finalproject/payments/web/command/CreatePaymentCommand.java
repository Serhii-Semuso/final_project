package com.epam.finalproject.payments.web.command;

import com.epam.finalproject.payments.Path;
import com.epam.finalproject.payments.db.dao.abstraction.EntityDao;
import com.epam.finalproject.payments.db.dao.imp.PaymentDaoImp;
import com.epam.finalproject.payments.db.entity.Payment;
import com.epam.finalproject.payments.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class CreatePaymentCommand implements Command {

    private static final Logger log = Logger.getLogger(CreatePaymentCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Command starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.warn("No user in session. Command finished");
            return Path.PAGE_LOGIN;
        }
        String errorMessage;

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String amountSt = request.getParameter("amount");
        String description = request.getParameter("description");

        if (amountSt == null || from == null || to == null) {
            errorMessage = "Amount/From/To cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_ERROR;
        }

        Long accountIdFrom = Long.parseLong(from);
        Long accountIdTo = Long.parseLong(to);
        BigDecimal amount = new BigDecimal(amountSt);
        log.info("User with id:" + user.getId() + " start creating payment from: " + accountIdFrom + ", to: " + accountIdTo + ", amount = " + amount + ", description=" + description);

        Payment payment = new Payment();
        payment.setAccountIdFrom(accountIdFrom);
        payment.setAccountIdTo(accountIdTo);
        payment.setDescription(description);
        payment.setAmount(amount);
        payment.setPaymentStatusId(1L);

        EntityDao<Payment> paymentDao = new PaymentDaoImp();
        paymentDao.insert(payment);

        log.debug("Command finished");
        return Path.COMMAND_USER_MAIN;
    }
}

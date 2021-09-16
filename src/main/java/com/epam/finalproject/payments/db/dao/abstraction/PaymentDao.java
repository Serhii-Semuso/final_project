package com.epam.finalproject.payments.db.dao.abstraction;

import com.epam.finalproject.payments.db.entity.Payment;

import java.util.Collection;

public interface PaymentDao extends EntityDao<Payment>{

    Collection<Payment> findByAccountId(Long id);
    Collection<Payment> findByUserId(Long id);

}

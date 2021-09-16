package com.epam.finalproject.payments.db.dao.abstraction;

import com.epam.finalproject.payments.db.entity.Account;

import java.util.Collection;

public interface AccountDao extends EntityDao<Account>{

        Collection<Account> findByUserId(Long id);

}

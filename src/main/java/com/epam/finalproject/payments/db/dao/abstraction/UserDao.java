package com.epam.finalproject.payments.db.dao.abstraction;

import com.epam.finalproject.payments.db.entity.User;

public interface UserDao extends EntityDao<User>{

    User findByLogin(String login);

}

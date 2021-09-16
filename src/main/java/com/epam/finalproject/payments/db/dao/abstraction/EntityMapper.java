package com.epam.finalproject.payments.db.dao.abstraction;

import java.sql.ResultSet;

public interface EntityMapper<T> {

    T mapRow(ResultSet resultSet);

}

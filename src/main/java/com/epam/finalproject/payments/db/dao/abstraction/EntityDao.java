package com.epam.finalproject.payments.db.dao.abstraction;

import java.util.Collection;

public interface EntityDao<T> {

    boolean insert(T t);
    T findById(Long id);
    boolean update(T t);
    boolean delete(T t);
    Collection<T> findAll();

}

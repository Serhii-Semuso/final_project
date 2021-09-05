package com.epam.finalproject.payments.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -6233941297233688789L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

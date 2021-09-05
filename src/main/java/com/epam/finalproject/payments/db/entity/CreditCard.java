package com.epam.finalproject.payments.db.entity;

import java.math.BigDecimal;

public class CreditCard extends Entity{

    private static final long serialVersionUID = -8879506750119948331L;

    private String number;
    private BigDecimal limit;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", limit=" + limit +
                '}';
    }
}

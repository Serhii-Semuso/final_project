package com.epam.finalproject.payments.db.entity;

import java.math.BigDecimal;

public class CreditCard extends Entity{

    private static final long serialVersionUID = -8879506750119948331L;

    private String number;
    private BigDecimal limit;
    private Long accountId;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}

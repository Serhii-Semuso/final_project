package com.epam.finalproject.payments.db.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends Entity {

    private static final long serialVersionUID = 8406958724579749596L;

    private BigDecimal amount;
    private String number;
    private String description;
    private Date creationDate;
    private Date sentDate;
    private Long accountIdFrom;
    private Long accountIdTo;
    private Long paymentStatusId;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(Long accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Long getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(Long accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public Long getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(Long paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", sentDate=" + sentDate +
                ", accountIdFrom=" + accountIdFrom +
                ", accountIdTo=" + accountIdTo +
                ", paymentStatusId=" + paymentStatusId +
                '}';
    }
}

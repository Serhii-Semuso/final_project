package com.epam.finalproject.payments.db.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends Entity {

    private static final long serialVersionUID = 8406958724579749596L;

    private BigDecimal amount;
    private Integer destinationAccountNumber;
    private String description;
    private Date creationDate;
    private Date sentDate;
    private Long accountId;
    private Long paymentStatusId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(Integer destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
                ", destinationAccountNumber=" + destinationAccountNumber +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", sentDate=" + sentDate +
                ", accountId=" + accountId +
                ", paymentStatusId=" + paymentStatusId +
                '}';
    }
}

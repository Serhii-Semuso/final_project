package com.epam.finalproject.payments.db.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Account extends Entity {

    private static final long serialVersionUID = -411147444219445774L;

    private String name;
    private BigDecimal balance;
    private Date creationDate;
    private Long userId;
    private boolean blocked;
    private boolean unblockRequest;
    private BigDecimal creditLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getUnblockRequest() {
        return unblockRequest;
    }

    public void setUnblockRequest(boolean unblockRequest) {
        this.unblockRequest = unblockRequest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", userId=" + userId +
                ", blocked=" + blocked +
                ", unblockRequest=" + unblockRequest +
                ", creditLimit=" + creditLimit +
                '}';
    }
}

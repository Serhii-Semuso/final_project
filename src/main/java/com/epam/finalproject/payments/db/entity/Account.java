package com.epam.finalproject.payments.db.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Account extends Entity {

    private static final long serialVersionUID = -411147444219445774L;

    private Integer number;
    private String name;
    private BigDecimal balance;
    private Date creationDate;
    private Long creditCardId;
    private Long userId;
    private boolean isBlocked;
    private boolean unblockRequest;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isUnblockRequest() {
        return unblockRequest;
    }

    public void setUnblockRequest(boolean unblockRequest) {
        this.unblockRequest = unblockRequest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", isBlocked=" + isBlocked +
                ", creditCardId=" + creditCardId +
                ", userId=" + userId +
                ", unblockRequest=" + unblockRequest +
                '}';
    }
}

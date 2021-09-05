package com.epam.finalproject.payments.db.entity;

public enum PaymentStatus {

    PREPARED, SENT;

    public static PaymentStatus getStatus(Payment payment) {
        Long statusId = payment.getPaymentStatusId();
        return PaymentStatus.values()[statusId.intValue()];
    }

    public String getName() {
        return name().toLowerCase();
    }

}

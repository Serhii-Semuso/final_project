package com.epam.finalproject.payments.db.dao.imp;

public final class Fields {




    private Fields(){}

    public static final String ENTITY_ID = "id";

    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_IS_BLOCKED = "is_blocked";

    public static final String ACCOUNT_NAME = "name";
    public static final String ACCOUNT_BALANCE = "balance";
    public static final String ACCOUNT_NUMBER = "number";
    public static final String ACCOUNT_CREATION_DATE = "creation_date";
    public static final String ACCOUNT_USER_ID = "user_id";
    public static final String ACCOUNT_IS_BLOCKED = "is_blocked";
    public static final String ACCOUNT_UNBLOCK_REQUEST = "unblock_request";
    public static final String ACCOUNT_CREDIT_LIMIT = "credit_limit";

    public static final String PAYMENT_AMOUNT = "amount";
    public static final String PAYMENT_NUMBER = "number";
    public static final String PAYMENT_DESCRIPTION = "description";
    public static final String PAYMENT_CREATION_DATE = "creation_date";
    public static final String PAYMENT_SENT_DATE = "sent_date";
    public static final String PAYMENT_ACCOUNT_ID_FROM = "account_id_from";
    public static final String PAYMENT_ACCOUNT_ID_TO = "account_id_to";
    public static final String PAYMENT_PAYMENT_STATUS_ID = "payment_status_id";

    public static final String CREDIT_CARD_LIMIT = "limit";
    public static final String CREDIT_CARD_NUMBER = "number";
    public static final String CREDIT_CARD_ACCOUNT_ID = "account_id";

}

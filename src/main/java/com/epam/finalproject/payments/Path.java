package com.epam.finalproject.payments;

public final class Path {


    private Path(){}

    public static final String PAGE_ERROR = "WEB-INF/error_page.jsp";
    public static final String PAGE_REGISTRATION = "WEB-INF/jsp/signup.jsp";
    public static final String PAGE_LOGIN = "login.jsp";
    public static final String PAGE_LIST_ACCOUNTS = "WEB-INF/jsp/client/list_account.jsp";
    public static final String PAGE_USER_MAIN = "WEB-INF/jsp/user_main.jsp";
    public static final String PAGE_OPEN_ACCOUNT = "WEB-INF/jsp/client/open_account.jsp";
    public static final String PAGE_REPLENISH_BALANCE = "WEB-INF/jsp/client/replenish_balance.jsp";
    public static final String PAGE_CREATE_PAYMENT = "WEB-INF/jsp/client/create_payment.jsp";
    public static final String PAGE_LIST_PAYMENTS = "WEB-INF/jsp/client/list_payment.jsp";

    public static final String COMMAND_USER_MAIN = "controller?command=userMain";
    public static final String COMMAND_VIEW_REGISTER = "controller?command=viewRegister";
    public static final String COMMAND_VIEW_ACCOUNTS = "controller?command=viewAccounts";
    public static final String COMMAND_VIEW_PAYMENTS = "controller?command=viewPayments";
    public static final String COMMAND_OPEN_ACCOUNT = "controller?command=openAccount";
    public static final String COMMAND_VIEW_OPEN_ACCOUNT = "controller?command=viewOpenAccount";

}

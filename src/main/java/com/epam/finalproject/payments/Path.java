package com.epam.finalproject.payments;

public final class Path {

    private Path(){}

    public static final String PAGE_ERROR = "WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_REGISTRATION = "WEB-INF/jsp/signup.jsp";
    public static final String PAGE_CLIENT_ACCOUNT = "WEB-INF/jsp/client/client_account.jsp";
    public static final String PAGE_LOGIN = "login.jsp";
    public static final String PAGE_VIEW_ACCOUNTS = "WEB-INF/jsp/client/list_account.jsp";

    public static final String COMMAND_VIEW_REGISTER = "controller?command=viewRegister";

}

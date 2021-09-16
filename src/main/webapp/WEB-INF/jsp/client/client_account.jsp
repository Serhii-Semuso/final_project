<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Account" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container">
    <a href="controller?command=viewAccounts" class="btn-sm btn-primary" role="button">View accounts</a>
    <a href="controller?command=viewPayments" class="btn-sm btn-primary" role="button">View payments</a>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
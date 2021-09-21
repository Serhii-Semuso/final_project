<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Accounts" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container">
    <form id="make_order" action="controller">
        <input type="hidden" name="command" value="makeOrder"/>
        <input type="submit" value='<fmt:message key="list_menu_jsp.button.make_an_order"/>'/>

        <table>
            <thead>
            <tr>
                <td>№</td>
                <td><fmt:message key="list_menu_jsp.table.header.name"/></td>
                <td><fmt:message key="list_menu_jsp.table.header.price"/></td>
                <td><fmt:message key="list_menu_jsp.table.header.order"/></td>
            </tr>
            </thead>

            <c:set var="k" value="0"/>
            <c:forEach var="account" items="${accounts}">
                <c:set var="k" value="${k+1}"/>
                <tr>
                    <td><c:out value="${k}"/></td>
                    <td>${account.number}</td>
                    <td>${account.balance}</td>
                    <td><input type="checkbox" name="accountId" value="${account.id}"/></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
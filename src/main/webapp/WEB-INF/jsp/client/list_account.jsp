<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Accounts" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container p-4">
    <div class="container d-flex align-content-center justify-content-center p-3">
        <h3>Your payment accounts</h3>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>No</td>
            <td>Name</td>
            <td>Number</td>
            <td>Balance</td>
            <td>Creation date</td>
            <td>Actions</td>
        </tr>
        </thead>

        <c:set var="k" value="0"/>
        <c:forEach var="account" items="${accounts}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>${account.name}</td>
                <td>${account.number}</td>
                <td>${account.balance}</td>
                <td>${account.creationDate}</td>

                <td>
                    <div class="row d-flex align-content-center justify-content-center">
                        <c:choose>

                            <c:when test="${account.blocked and not account.unblockRequest}">
                                <div class="col d-flex align-content-center justify-content-center">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="unblockRequest">
                                        <input type="hidden" name="accountId" value="${account.id}">
                                        <button type="submit" class="btn btn-danger">Unblock request</button>
                                    </form>
                                </div>
                            </c:when>

                            <c:when test="${not account.blocked}">

                                <div class="col d-flex align-content-center justify-content-center">

                                    <form action="controller" method="get">
                                        <input type="hidden" name="command" value="viewReplenishBalance">
                                        <input type="hidden" name="accountId" value="${account.id}">
                                        <button type="submit" class="btn btn-success">Replenish</button>
                                    </form>

                                </div>

                                <div class="col d-flex align-content-center justify-content-center">

                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="blockAccount">
                                        <input type="hidden" name="accountId" value="${account.id}">
                                        <button type="submit" class="btn btn-danger">Block</button>
                                    </form>
                                </div>

                            </c:when>

                            <c:otherwise>
                                No actions...
                            </c:otherwise>

                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
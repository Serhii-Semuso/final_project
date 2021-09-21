<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Payments" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container p-4">
    <div class="container d-flex align-content-center justify-content-center p-3">
        <h3>Your payments</h3>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>No</td>
            <td>From</td>
            <td>To</td>
            <td>Amount</td>
            <td>Description</td>
            <td>Creation date</td>
            <td>Sent date</td>
            <td>Status</td>
            <td>Actions</td>
        </tr>
        </thead>

        <c:set var="k" value="0"/>
        <c:forEach var="payment" items="${payments}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td><c:out value="${k}"/></td>
                <td>${payment.accountIdFrom}</td>
                <td>${payment.accountIdTo}</td>
                <td>${payment.amount}</td>
                <td>${payment.description}</td>
                <td>${payment.creationDate}</td>
                <td>${payment.sentDate}</td>
                <td>${payment.paymentStatusId}</td>

                <td>
                    <div class="row d-flex align-content-center justify-content-center">
                        <div class="col d-flex align-content-center justify-content-center">
                            <c:choose>

                                <c:when test="${payment.paymentStatusId eq 1}">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="sendPayment">
                                        <input type="hidden" name="accountId" value="${payment.id}">
                                        <button type="submit" class="btn btn-primary">Send</button>
                                    </form>
                                </c:when>

                                <c:otherwise>
                                    Payment sent.
                                </c:otherwise>

                            </c:choose>
                        </div>
                    </div>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
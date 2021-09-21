<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="New payment" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <div class="container d-flex align-content-center justify-content-center p-3">
        <h3>Create payment</h3>
    </div>

    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-10 col-md-8 col-lg-6">
                <form action="controller" method="post" class="needs-validation" novalidate>
                    <input type="hidden" name="command" value="createPayment">
                    <div class="col">
                        <div class="form-group">
                            <label for="from">Account from:</label>
                            <select class="form-control" id="from" name="from">
                                <c:forEach var="account" items="${userAccounts}">
                                    <option name="from" value="${account.id}">${account.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="to">Account to:</label>
                            <select class="form-control" id="to" name="to">
                                <c:forEach var="account" items="${allAccounts}">
                                    <option name="to" value="${account.id}">${account.number}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="amount">Amount:</label>
                            <input type="text" class="form-control" id="amount" placeholder="Choose account to" name="amount"
                                   required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <textarea class="form-control" id="description" rows="3" name="description"></textarea>
                        </div>
                    </div>
                    <div class="mx-auto text-center">
                        <button type="submit" class="btn btn-primary">Create payment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</body>
</html>
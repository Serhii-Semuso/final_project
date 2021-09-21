<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Open account"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container" style="height: 85%;">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6">
            <form action="controller" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="command" value="openAccount">
                    <div class="col">
                        <div class="form-group">
                            <label for="name">Account name:</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter account name" name="name"
                                   required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                <div class="mx-auto text-center">
                    <button type="submit" class="btn btn-primary">Create payment account</button>
                </div>
            </form>
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

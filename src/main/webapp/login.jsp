<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Log in"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">

<div class="container" style="height: 90%;">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6">
            <form action="controller" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="command" value="login">
                <div class="form-group">
                    <label for="log">Login:</label>
                    <input type="text" class="form-control" id="log" placeholder="Enter login" name="login" required>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
                <div class="mx-auto text-center">
                    <button type="submit" class="btn btn-primary">Log in</button>
                    <br/>
                    <p class="text-muted">or</p>
                    <a href="controller?command=viewRegister" class="btn btn-secondary btn-sm" role="button">Sing up</a>
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
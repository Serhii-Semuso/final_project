<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Sign up"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">

<div class="container" style="height: 90%;">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6">
            <form action="controller" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="command" value="register">
                <div class="row mb-4">
                    <div class="col">
                        <div class="form-group">
                            <label for="login">Login:</label>
                            <input type="text" class="form-control" id="login" placeholder="Enter login" name="login"
                                   required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter password"
                                   name="password">
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col">
                        <div class="form-group">
                            <label for="firstName">First name:</label>
                            <input type="text" class="form-control" id="firstName" placeholder="Enter first name"
                                   name="firstName" required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="lastName">Last name:</label>
                            <input type="text" class="form-control" id="lastName" placeholder="Enter last name"
                                   name="lastName"
                                   required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" class="form-control" id="email" placeholder="Enter email" name="email"
                                   required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="phoneNumber">Phone number:</label>
                            <input type="text" class="form-control" id="phoneNumber" placeholder="Enter phone number"
                                   name="phoneNumber" required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                </div>
                <div class="mx-auto text-center">
                    <button type="submit" class="btn btn-primary">Sign up</button>
                    <p class="text-muted"><br/>have an account?</p>
                    <a href="login.jsp" class="btn btn-secondary btn-sm" role="button">Log in</a>
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

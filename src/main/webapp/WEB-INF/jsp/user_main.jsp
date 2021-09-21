<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

    <c:set var="title" value="Home" scope="page"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>

    <body class="d-flex flex-column min-vh-100">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="p-5 text-center bg-light">
            <h1 class="mb-3">PAYMENT SYSTEM</h1>
            <h4 class="mb-3">Best system for your payments!</h4>
            <a class="btn btn-primary" href="controller?command=viewOpenAccount" role="button">Open payment account</a>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
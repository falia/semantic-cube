<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <%--<link rel="stylesheet" type="text/css" href="webjars/bootstrap-select/1.6.3/dist/css/bootstrap-select.min.css">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">


    <c:url value="/css/styles.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    <script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
    <%--<script type="text/javascript" src="webjars/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>--%>


</head> 

<body>


<c:url value="/js/search.js" var="searchJs" />
<script src="${searchJs}"></script>

<%@ include file="header.jsp" %>

<div class="main container">

    <div class="starter-template">
        <h1>Welcome to SemanticCube</h1>
    </div>

</div>


</body>

</html>
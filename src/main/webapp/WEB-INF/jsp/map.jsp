<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>

    <c:url value="/css/styles.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

    <c:url value="/css/ol.css" var="olCss" />
    <link href="${olCss}" rel="stylesheet" />

</head>

<body>
<script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ include file="header.jsp" %>

<div class="container">

    <div class="starter-template">
        <h1>Map</h1>
    </div>

    <div id="map" class="map" tabindex="0"></div>

</div>


<c:url value="/js/ol.js" var="oljs" />
<script src="${oljs}"></script>

<c:url value="/js/map.js" var="mapjs" />
<script src="${mapjs}"></script>

</body>

</html>
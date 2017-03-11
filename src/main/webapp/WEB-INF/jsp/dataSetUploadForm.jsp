<%--
  Created by IntelliJ IDEA.
  User: piraujo
  Date: 11/03/2017
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>DataSet Upload</title>
    <script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>

    <c:url value="/css/styles.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
</head>
<body>

<c:url value="/js/addDataSet.js" var="addDataSet" />
<script src="${addDataSet}"></script>
<%@ include file="header.jsp" %>
<h2>Upload a new DataSet</h2>
<form id="datasetForm">
    <div>DataSet title:</div>
    <input class="title" type="text" name="title"><br>
    <div>DataSet desscription:</div>
    <input class="descr" type="text" name="description"><br>
    <fieldset id="dataFieldSet">
        <div>
            Datasource:
        </div>
        <div>
            Type:
        </div>
        <input class="type" type="text" name="type1"><br>
        <div>
            URL:
        </div>
        <input class="url" type="text" name="url1"><br>
    </fieldset>
    <div id="addsource">New Datasource</div>
    <fieldset id="tags">
        <div>
            Tag:
        </div>
        <input type="text" name="tag1"><br>
    </fieldset>
    <div id="addTag">New Tag</div>
    <div id="submitButton">Submit</div>
</form>
</body>
</html>

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
    <script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.12.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>


<c:url value="/js/search.js" var="searchJs" />
<script src="${searchJs}"></script>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.10.0/styles/default.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.10.0/highlight.min.js"></script>

<c:url value="/js/sparql.js" var="sparqlJs" />
<script src="${sparqlJs}"></script>


<%@ include file="header.jsp" %>

<div class="main container">

    <div style="margin-bottom: 20px">

        <h3>SPARQL query</h3>

        <textarea rows="10" class="form-control" id="query"></textarea>

        <br />
        <select class="form-control" id="format">
            <option>JSON</option>
            <option>XML</option>
            <option>CSV</option>
        </select>
        <br/>
        <button id="sparqlSubmitButton" class="btn btn-default float-right">Submit</button>


    </div>

    <hr />

    <div id="result">
        <h3>Search Results</h3>
        <div id="content" style="overflow-y: auto; height: 300px;">
            <pre>
                <code>

                </code>
            </pre>
        </div>
    </div>

</div>


</body>

</html>
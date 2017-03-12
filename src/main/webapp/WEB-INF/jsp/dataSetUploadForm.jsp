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
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>DataSet Upload</title>
    <script type="text/javascript" src="webjars/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/jquery-ui/1.12.1/jquery-ui.min.js"></script>
    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>

    <spring:url value="/css/styles.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
<spring:url value="/js/dynamic_list_helper.js" var="listHelper" />
<spring:url value="/js/addDataSet.js" var="addDataSet" />
<script src="${addDataSet}"></script>
<script src="${listHelper}"></script>
<%@ include file="header.jsp" %>
<div class="main container" id="dataset">
    <h2>Upload a new DataSet</h2>
    <form id="datasetForm" class="form-group" action="#" th:action="@{/datasetupload}" th:object="${dataset}" method="post">
        <div>DataSet title:</div>
        <input class="title form-control" type="text" name="title" th:field="${dataset.title}"/><br>
        <div>DataSet description:</div>
        <textarea class="descr form-control" type="textArea" name="description" th:field="${dataset.description}" /><br>
        <div>Publisher:</div>
        <input class="form-control" type="text" name="publisher" th:field="${dataset.publisher}"><br>
        <div>Theme</div>
        <input class="form-control" type="text" id="themeSelect"  name="themeSelect" />


        <ul id="distributionListContainer"></ul>
        <c:forEach items="${dataset.distributionList}" var="Distribution" varStatus="i" begin="0" >
            <li class="distribution">
        <fieldset  class="fieldset">
            <div>
                Datasource:
            </div>
            <div>
                Type:
            </div>
            <input class="type form-control" type="text" name="resourceList[${i.index}].type" value=""><br>
            <div>
                URL:
            </div>
            <input class="url form-control" type="text" name="resourceList[${i.index}].url" value=""><br>
            <div>
                Description:
            </div>
            <input class="datadescr form-control" type="text" name="resourceList[${i.index}].descr" value=""><br>
            <div>
                Format:
            </div>
            <input class="format form-control" type="text" name="resourceList[${i.index}].format" value=""><br>
            <div>
                Size:
            </div>
            <input class="size form-control" type="text" name="resourceList[${i.index}].size" value=""><br>
        </fieldset>
            </li>
        </c:forEach>
        <c:if test="${dataset.distributionList.size() == 0}">
        <li class="distribution defaultRow">
            <fieldset  class="fieldset">
                <div>
                    Datasource:
                </div>
                <div>
                    Type:
                </div>
                <input class="type form-control" type="text" name="resourceList[].type" value=""><br>
                <div>
                    URL:
                </div>
                <input class="url form-control" type="text" name="resourceList[].url" value=""><br>
                <div>
                    Description:
                </div>
                <input class="datadescr form-control" type="text" name="resourceList[].descr" value=""><br>
                <div>
                    Format:
                </div>
                <input class="format form-control" type="text" name="resourceList[].format" value=""><br>
                <div>
                    Size:
                </div>
                <input class="size form-control" type="text" name="resourceList[].size" value=""><br>
            </fieldset>
        </li>
        </c:if>
        </ul>
        <div id="addsource" class="dataSetButton">New Resource</div>

        <input type="submit" id="submitButton" class="dataSetButton"/>
    </form>
</div>

</body>
</html>

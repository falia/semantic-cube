<%--
  Created by IntelliJ IDEA.
  User: piraujo
  Date: 11/03/2017
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Game of code</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:url value="/map" var="map" />
                <li><a href="${map}">Map</a></li>
                <c:url value="/sparql" var="sparql" />
                <li><a href="${sparql}">Sparql</a></li>
                <c:url value="/datasetupload" var="datasetupload"/>
                <li><a href="${datasetupload}">DataSet Upload</a> </li>
            </ul>
        </div>
    </div>
</nav>
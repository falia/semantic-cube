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
            <a class="navbar-brand" href="#">SemanticCube</a>
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

            <div class="col-sm-3 col-md-3 pull-right">
                <div class="navbar-form">
                    <div class="input-group">
                        <input type="text" class="form-control" id="searchField" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" id="searchButton"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</nav>
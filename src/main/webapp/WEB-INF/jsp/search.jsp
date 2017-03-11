<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="searchResult">
    <hgroup class="mb20">
        <h1>Search Results</h1>
        <h2 class="lead"><strong class="text-danger"><c:out value="${fn:length(results)}"/></strong> results were found for the search for <strong class="text-danger"><c:out value="${searchValue}"/></strong></h2>
    </hgroup>

    <div>
        <section class="col-xs-12 col-sm-6 col-md-12">
            <c:forEach items="${results}" var="current">

                <article class="search-result row">

                    <c:url value="/images/dataset.png" var="datasetPng"/>

                    <!-- icon -->
                    <div class="col-xs-12 col-sm-12 col-md-3">
                        <a href="#" title="dataset" class="thumbnail"><img src="${datasetPng}" alt="dataset"></a>
                    </div>

                    <!-- meta data -->
                    <div class="col-xs-12 col-sm-12 col-md-2">
                        <ul class="meta-search">
                            <li><i class="glyphicon glyphicon-calendar"></i> <span>02/15/2014</span></li>
                            <li><i class="glyphicon glyphicon-time"></i> <span>4:28 pm</span></li>
                            <li><i class="glyphicon glyphicon-tags"></i> <span>People</span></li>
                        </ul>
                    </div>

                    <!-- title and description -->
                    <div class="col-xs-12 col-sm-12 col-md-7 excerpet">
                        <h3><a href="#" title=""><c:out value="${current.title}"/></a></h3>
                        <p><c:out value="${current.description}"/></p>
                    <span class="plus"><a href="#" title="Lorem ipsum"><i
                            class="glyphicon glyphicon-plus"></i></a></span>
                    </div>
                    <span class="clearfix borda"></span>
                </article>

            </c:forEach>
        </section>
    </div>
</div>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>"/>
</head>
<body>
<div class="page">
    <div class="left col-md-3">
    </div>
    <div class="content col-md-6">
        <tiles:insertAttribute name="content"/>
    </div>
    <div class="right col-md-3">
    </div>
</div>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"/>"/>
<script type="text/javascript" src="<c:url value="/webjars/jquery/3.2.1/jquery.min.js"/>"/>
</body>
</html>
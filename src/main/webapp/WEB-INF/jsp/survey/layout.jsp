<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet"
          href="/survey/webjars/bootstrap/3.3.5/css/bootstrap.min.css"/>
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
<script type="text/javascript"
        src="/survey/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="/survey/webjars/jquery/2.1.4/jquery.min.js"></script>
</body>
</html>
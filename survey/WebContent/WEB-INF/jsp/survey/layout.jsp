<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<link rel="stylesheet"
    href="/survey/webjars/bootstrap/3.2.0/css/bootstrap.min.css" />
</head>
<body>
    <div class="page">
        <div class="content">
            <tiles:insertAttribute name="content" />
        </div>
    </div>
    <script type="text/javascript"
        src="/survey/webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"
        src="/survey/webjars/jquery/2.1.1/jquery.min.js"></script>
</body>
</html>
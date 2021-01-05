<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextRoot}/css/login.styles.css" rel="stylesheet">
    <title><tiles:insertAttribute name="title"/></title>
</head>
<body>

    <main class="container-fluid">
        <tiles:insertAttribute name="content"/>
    </main>

    <script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
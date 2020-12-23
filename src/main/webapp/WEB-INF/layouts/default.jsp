<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="с" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

    <link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
    <style>form { margin-bottom: 0; }</style>

    <title><tiles:insertAttribute name="title"/></title>
</head>
<body>

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
        <div class="container-fluid">
            <a class="navbar-brand" href="${contextRoot}/">Ordjo</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample03">
                <ul class="navbar-nav me-auto mb-2 mb-sm-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${contextRoot}/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${contextRoot}/about">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="${contextRoot}/addstatus">Add Status</a>
                    </li>
                </ul>
                <form>
                    <a class="btn btn-light" href="#" role="button">Sing Up</a>
                </form>
            </div>
        </div>
    </nav>

    <main class="container">
      <tiles:insertAttribute name="content"/>
    </main>

    <script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
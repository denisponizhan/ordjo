<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
    <link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextRoot}/css/common.styles.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

    <title><tiles:insertAttribute name="title"/></title>
</head>
<body>

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
        <div class="container">
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
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="${contextRoot}/addstatus">Add Status</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="${contextRoot}/viewstatus">View Status</a>
                        </li>
                    </sec:authorize>
                </ul>

                <sec:authorize access="!isAuthenticated()">
                    <a class="btn btn-light me-2" href="${contextRoot}/login" role="button">Sing In</a>
                    <a class="btn btn-light" href="${contextRoot}/register" role="button">Sing Up</a>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <a class="link-light profile-link" href="${contextRoot}/profile">Profile</a>
                    <a class="btn btn-light ms-3" href="javascript:document.getElementById('logoutForm').submit();" role="button">Log Out</a>
                </sec:authorize>
            </div>
        </div>
    </nav>

    <c:url var="logoutLink" value="${contextRoot}/logout" />
    <form id="logoutForm" method="post" action="${logoutLink}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <main class="container">
        <tiles:insertAttribute name="content"/>
    </main>

    <script src="${contextRoot}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
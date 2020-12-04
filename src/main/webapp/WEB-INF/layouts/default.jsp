<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="с" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <!-- Le styles -->
    <c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

    <link href="${contextRoot}/css/bootstrap.css" rel="stylesheet">
    <link href="${contextRoot}/css/bootstrap-responsive.css" rel="stylesheet">
    <style>body { padding-top: 50px; }</style>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->
    <title><tiles:insertAttribute name="title"/></title>
</head>
<body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/">Ordjo</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="active"><a href="${contextRoot}/">Home</a></li>
              <li><a href="${contextRoot}/about">About</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <h1>Starter template</h1>
      <tiles:insertAttribute name="content"/>
    </div>

    <!-- Le javascript ================================================== -->
    <script src="${contextRoot}/js/jquery.js"></script>
    <script src="${contextRoot}/js/bootstrap.js"></script>
</body>
</html>
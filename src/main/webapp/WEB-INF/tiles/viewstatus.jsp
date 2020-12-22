<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>

<div class="row">
    <div class="mb-3">

        <jwp:pagination page="${page}" url="${contextRoot}/viewstatus" size="10"/>

        <c:forEach var = "statusUpdate" items = "${page.content}">
            <p><c:out value = "${statusUpdate.text}" /></p>
        </c:forEach>
    </div>
</row>

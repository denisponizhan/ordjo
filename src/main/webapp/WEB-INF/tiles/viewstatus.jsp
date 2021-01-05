<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>

<div class="row">
    <div class="page pt-2 pb-2">
        <div class="mb-3">

            <c:forEach var = "statusUpdate" items = "${page.content}">
                <c:url var = "editLink" value = "/editstatus?id=${statusUpdate.id}" />
                <c:url var = "deleteLink" value = "/deletestatus?id=${statusUpdate.id}" />
                <div>${statusUpdate.text}</div>
                <div>${statusUpdate.added}</div>
                <div><a href="${editLink}">Edit</a> | <a href="${deleteLink}">Delete</a></div>
            </c:forEach>

             <jwp:pagination page="${page}" url="${contextRoot}/viewstatus" size="3"/>
        </div>
    <div>
</div>

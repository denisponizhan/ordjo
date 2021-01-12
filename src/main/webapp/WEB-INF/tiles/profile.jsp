<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>

<c:url var="img" value="/img" />
<c:url var="editProfileText" value="/edit-profile-text" />

<div class="row">
    <div class="page pt-2 pb-2">
        <img src="${img}/avatar.jpg" alt="avatar" />

        <c:choose>
            <c:when test="${profile.about == null}">
                Click edit to add an information about yourself to your profile.
            </c:when>
            <c:otherwise>
               ${profile.about}
            </c:otherwise>
        </c:choose>

        <a href="${editProfileText}">Edit</a>
    <div>
</div>

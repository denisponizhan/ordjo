<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>

<c:url var="profilePhoto" value="/profilephoto" />
<c:url var="editProfileText" value="/edit-profile-text" />

<div class="row">
    <div class="page pt-2 pb-2">
        <div>
            <img src="${profilePhoto}" alt="avatar" />
        </div>
        <div>
            <c:choose>
                <c:when test="${profile.about == null}">
                    Click edit to add an information about yourself to your profile.
                </c:when>
                <c:otherwise>
                   ${profile.about}
                </c:otherwise>
            </c:choose>
        </div>
        <div>
            <a href="${editProfileText}">Edit</a>
        </div>
        <div>
            <c:url value="/upload-profile-photo" var="uploadPhotoLink" />
            <form method="post" enctype="multipart/form-data" action="${uploadPhotoLink}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                select photo: <input type="file" accept="image/*" name="file" />
                <input type="submit" value="Upload"/>
            </form>
        </div>


    <div>
</div>

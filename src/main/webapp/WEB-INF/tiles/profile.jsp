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
            <img id="profilePhotoImage" src="${profilePhoto}" alt="avatar" />
            <a href="#" id="uploadLink">Upload Photo</a>
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
            <c:url value="/upload-profile-photo" var="uploadPhotoLink"/>
            <form id="photoUploadForm" method="post" enctype="multipart/form-data" action="${uploadPhotoLink}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input id="photoFileInput" type="file" accept="image/*" name="file" />
                <input type="submit" value="Upload"/>
            </form>
        </div>
    <div>
</div>

<script>

    function uploadSuccess(data) {
        $("#profilePhotoImage").attr("src", "${profilePhoto}");
        $("#photoFileInput").val("");
    }

    function uploadPhoto(e) {
        e.preventDefault();
        $.ajax({
            url: $(this).attr("action"),
            type: "POST",
            data: new FormData(this),
            processData: false,
            contentType: false,
            success: uploadSuccess,
            error: function(e) {
                console.log(e);
            }
        });
    }

    $(document).ready(function() {
        $("#uploadLink").click(function(e) {
            e.preventDefault();
            $("#photoFileInput").trigger('click');
        });

        $("#photoFileInput").change(function() {
            $("#photoUploadForm").submit();
        });

        $("#photoUploadForm").on('submit', uploadPhoto);
    });

</script>

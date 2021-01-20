<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jwp" tagdir="/WEB-INF/tags" %>

<c:url var="profilePhoto" value="/profilephoto/${userId}" />
<c:url var="editProfileText" value="/edit-profile-text" />
<c:url var="saveInterest" value="/save-interest" />
<c:url var="deleteInterest" value="/delete-interest" />

<div class="row">
    <div class="page pt-2 pb-2">

        <div id="interestDiv">

            <ul id="interestList">
                <c:choose>
                    <c:when test="${empty profile.interests}">
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="interest" items="${profile.interests}">
                            <li>${interest.name}</li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

        <div>
            <img id="profilePhotoImage" src="${profilePhoto}" alt="avatar" />
            <c:if test="${ownProfile}">
                <a href="#" id="uploadLink">Upload Photo</a>
            </c:if>
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
            <c:if test="${ownProfile}">
                <a href="${editProfileText}">Edit</a>
            </c:if>
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

    function editInterest(text, actionUrl) {
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        $.ajaxPrefilter(function(options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader(header, token);
        });

        $.ajax({
            url: actionUrl,
            data: {
                name: text
            },
            type: 'POST',
            success: function() {
                console.log("ok");
            }
        });
    }

    function saveInterest(text) {
        editInterest(text, "${saveInterest}");
    }

    function deleteInterest(text) {
        editInterest(text, "${deleteInterest}");
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

        $("#interestList").tagit({
            caseSensitive: false,
            allowSpaces: true,
            tagLimit: 10,
            readOnly: ${!ownProfile},
            afterTagRemoved: function(e, ui) {
                deleteInterest(ui.tagLabel);
            },
            afterTagAdded: function(e, ui) {
                if (!ui.duringInitialization) {
                    saveInterest(ui.tagLabel);
                }
            }
        });
    });

</script>

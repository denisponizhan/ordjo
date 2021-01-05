<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="page pt-2 pb-2">
        <div class="mb-3">
          <form:form modelAttribute="statusUpdate">
              <form:errors path="text" />
              <form:textarea path="text" name="text" class="form-control" rows="3"></form:textarea>
              <button type="submit" class="btn btn-primary mb-3">Confirm</button>
          </form:form>
        </div>
        <div class="mb-3">
           <p>Status added at <fmt:formatDate pattern="H:mm:s" value="${latestStatusUpdate.added}"/></p>
           <p><c:out value="${latestStatusUpdate.text}"/></p>
        </div>
    </div>
</div>

<script src="https://cdn.tiny.cloud/1/lqx5gnjaja77k8o25f2fy6x27nxem0b00o650x1j2x9wqjf1/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<script>
    tinymce.init({
      selector: "textarea",
      plugins: "link"
    });
</script>

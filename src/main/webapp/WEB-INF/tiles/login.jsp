<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="loginUrl" value="/login"/>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class="full-height row justify-content-center align-items-center">
    <form class="login-form rounded" method="post" action="${loginUrl}">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                Incorrect username or password.
            </div>
        </c:if>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-floating mb-3">
            <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Username">
            <label for="floatingInput">Username</label>
        </div>
        <div class="form-floating mb-3">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>
        <button type="submit" class="btn btn-primary">Sign In</button>
    </form>
</div>
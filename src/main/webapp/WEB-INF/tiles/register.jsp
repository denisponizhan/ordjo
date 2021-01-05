<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class="full-height row justify-content-center align-items-center">
    <form:errors path="email" />
    <form:errors path="password" />
    <form:form class="login-form rounded" method="post" modelAttribute="user">
        <div class="form-floating mb-3">
            <form:input type="text" path="email" class="form-control" id="floatingInput" placeholder="Email"/>
            <label for="floatingInput">Username</label>
        </div>
        <div class="form-floating mb-3">
            <form:input type="password" path="password" class="form-control" id="floatingPassword" placeholder="Password"/>
            <label for="floatingPassword">Password</label>
        </div>
        <div class="form-floating mb-3">
            <input type="password" name="repeatpassword" class="form-control" id="floatingRepeatPassword" placeholder="Repeat Password"/>
            <label for="floatingPassword">Repeat Password</label>
        </div>
        <button type="submit" class="btn btn-primary">Sign Up</button>
    </form:form>
</div>
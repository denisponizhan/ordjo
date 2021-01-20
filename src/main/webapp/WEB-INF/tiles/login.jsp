<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="loginUrl" value="/login"/>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class="full-height row justify-content-center align-items-center">
    <div class="col-xs-12 col-sm-10 col-md-6 d-flex align-items-center flex-column">
        <a class="ordjo-brand auth-brand mb-3" href="${contextRoot}">Ordjo</a>
        <form class="login-form rounded" method="post" action="${loginUrl}">
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    Incorrect email or password.
                </div>
            </c:if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-floating mb-3">
                <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Email">
                <label for="floatingInput">Email</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>
            <div class="row">
                <div class="col-auto me-auto">
                    <button type="submit" class="btn btn-primary">Sign In</button>
                </div>
                <div class="col-auto">
                    <a class="btn btn-light" href="${contextRoot}/register" role="button">Don't have an account?</a>
                </div>
            </div>
        </form>
    </div>
</div>
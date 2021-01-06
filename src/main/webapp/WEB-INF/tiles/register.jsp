<%@ page language="java" contentType="text/html; charset=UTF=8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class="full-height row justify-content-center align-items-center">
    <form:form class="login-form rounded" method="post" modelAttribute="user">
        <form:errors path="*" class="alert alert-danger" role="alert" element="div"/>
        <div class="form-floating mb-3">
            <form:input type="text" path="email" class="form-control" id="floatingInput" placeholder="Email"/>
            <label for="floatingInput">Email</label>
        </div>
        <div class="form-floating mb-3">
            <form:input type="password" path="plainPassword" class="form-control" id="floatingPassword" placeholder="Password"/>
            <label for="floatingPassword">Password</label>
        </div>
        <div class="form-floating mb-3">
            <form:input type="password" path="repeatPassword" class="form-control" id="floatingRepeatPassword" placeholder="Repeat Password"/>
            <label for="floatingPassword">Repeat Password</label>
        </div>
        <div class="row">
            <div class="col-auto me-auto">
                <button type="submit" class="btn btn-primary">Sign Up</button>
            </div>
            <div class="col-auto">
                <a class="btn btn-light" href="${contextRoot}/login" role="button">Already have an account?</a>
            </div>
        </div>
    </form:form>
</div>
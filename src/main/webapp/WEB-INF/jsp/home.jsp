<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:sec="http://www.springframework.org/security/tags">
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>

    <link href="css/bootstrap.css" rel="stylesheet"></link>
    <script src="js/jquery-2.2.0.js" ></script>
    <script src="js/bootstrap.js" ></script>
</head>
<body>
    <nav role="navigation">
        <ul>
            <c:if test="${empty currentUser}">
                <li><a href="/login">Log in</a></li>
            </c:if>
            <c:if test="${not empty currentUser}">
                <li>
                    <form action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit">Log out</button>
                    </form>
                </li>
                <li><a href="/user/${currentUser.id}">View myself</a></li>
            </c:if>

            <sec:authorize access="hasAuthority('ADMIN')">
                <li><a href="/users">Danh sách người dùng</a></li>
                <li><a href="/classrooms">Danh sách phòng học</a></li>
                <li><a href="/courses">Danh sách lớp học</a></li>
            </sec:authorize>

            <sec:authorize access="hasAnyAuthority('GIAO_VIEN','USER')">
                <li><a href="/courses/my-courses">Danh sách lớp học</a></li>
            </sec:authorize>
        </ul>
    </nav>
</body>
</html>
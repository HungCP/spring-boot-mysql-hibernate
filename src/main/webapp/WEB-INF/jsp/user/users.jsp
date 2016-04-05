<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
    <meta charset="utf-8">
    <title>List of Users</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Tạo người dùng mới</a></li>
      </ul>
    </nav>

    <h1>Danh sách người dùng</h1>

    <table class="table">
      <thead>
        <tr>
           <th>Mã</th>
            <th>Họ</th>
            <th>Tên</th>
            <th>E-mail</th>
            <th>Role</th>
            <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
        <tr>
            <td><a href="/user/${user.id}">${user.ma}</a></td>
            <td>${user.lastName}</td>
            <td>${user.firstName}</td>
            <td>${user.email}</td>
            <td>${user.role.text}</td>
            <td>
                <spring:url value="/user/${user.id}/delete" var="deleteUrl" />
                <spring:url value="/user/${user.id}/update" var="updateUrl" />

                <button onclick="location.href='${updateUrl}'">Update</button>
                <button onclick="location.href='${deleteUrl}'">Delete</button>

            </td>
        </tr>
        </c:forEach>
      </tbody>
    </table>

</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>
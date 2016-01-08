<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">

    <style>
      table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
      }
      th, td {
        padding: 5px;
        text-align: left;
      }
    </style>

    <title>List of Users</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Tạo người dùng mới</a></li>
      </ul>
    </nav>

    <h1>List of Users</h1>

    <table style="width:45%">
      <thead>
        <tr>
           <th>Mã</th>
          <th>Họ</th>
          <th>Tên</th>
          <th>E-mail</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
          <tr>
              <td><a href="/user/${user.id}">${user.ma}</a></td>
            <td>${user.lastName}</td>
            <td>${user.firstName}</td>
            <td><a href="/user/${user.id}">${user.email}</a></td>
            <td>${user.role.text}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

</body>

</html>
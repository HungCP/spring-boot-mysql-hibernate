<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 01/05/2016
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
    <title>List of Course</title>
</head>
<body>

    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
      </ul>
    </nav>

    <h1>Danh sách lớp học</h1>

    <table style="width:45%">
      <thead>
      <tr>
        <th>Mã lớp</th>
        <th>Tên lớp</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${courses}" var="course" varStatus="status">
        <tr>
          <td><a href="/course/${course.id}">${course.ma}</a></td>
          <td>${course.name}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

</body>
</html>

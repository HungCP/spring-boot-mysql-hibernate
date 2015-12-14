<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/14/2015
  Time: 10:27 AM
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
    <title>List of Classrooms</title>
</head>
<body>

    <h1>Danh sách phòng học</h1>

    <table style="width:45%">
      <thead>
      <tr>
        <th>Mã phòng</th>
        <th>Tên phòng</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach items="${classrooms}" var="classroom" varStatus="status">
          <tr>
            <td><a href="/classroom/${classroom.id}">${classroom.ma}</a></td>
            <td>${classroom.name}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

</body>
</html>

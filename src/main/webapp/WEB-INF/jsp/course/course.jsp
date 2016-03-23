<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/15/2015
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Course details</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/courseAttendance/create/${model.course.id}">Tạo buổi học</a></li>
      </ul>
    </nav>

    <h1>${model.course.ma} - ${model.course.name}</h1>

    <h2>Giáo Viên Chủ Nhiệm: ${model.giaoVien.name}</h2>

    <table style="width:45%">
        <thead>
        <tr>
            <th>Mã sinh viên</th>
            <th>Họ</th>
            <th>Tên</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${model.sinhVienList}" var="sinhVien" varStatus="status">
            <tr>
                <td><a href="/user/${sinhVien.id}">${sinhVien.ma}</a></td>
                <td>${sinhVien.lastName}</td>
                <td>${sinhVien.firstName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h1>Danh sách buổi học</h1>

    <table style="width:45%">
        <thead>
        <tr>
            <th>Id</th>
            <th>Tiêu đề</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${model.courseAttendanceList}" var="courseAttendance" varStatus="status">
            <tr>
                <td><a href="/courseAttendance/${courseAttendance.id}">${courseAttendance.id}</a></td>
                <td>${courseAttendance.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</body>
</html>

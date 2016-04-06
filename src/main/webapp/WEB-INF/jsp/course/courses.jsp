<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/15/2015
  Time: 12:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
    <title>List of Course</title>
</head>
<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/course/create">Tạo lớp mới</a></li>
      </ul>
    </nav>

    <h1>Danh sách lớp học</h1>

    <table class="table table-bordered table-hover">
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

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

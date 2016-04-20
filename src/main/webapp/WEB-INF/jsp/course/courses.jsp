<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/15/2015
  Time: 12:04 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>List of Course</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/course/create">Tạo lớp mới</a></li>
      </ul>
    </nav>

    <div class="container">
        <h1>Danh sách lớp học</h1>

        <table class="table table-bordered table-hover span12">
          <thead>
          <tr>
            <th>Mã lớp</th>
            <th>Tên lớp</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${courses}" var="course" varStatus="status">
            <tr>
              <td><a href="/course/${course.id}/update">${course.ma}</a></td>
              <td>${course.name}</td>
              <td>
                  <spring:url value="/course/${course.id}/attendance" var="attendanceUrl" />
                  <spring:url value="/course/${course.id}/delete" var="deleteUrl" />
                  <spring:url value="/course/${course.id}/update" var="updateUrl" />

                  <button class="btn btn-success btn-sm" onclick="location.href='${attendanceUrl}'">Điểm danh</button>
                  <button class="btn btn-primary btn-sm" onclick="location.href='${updateUrl}'">Cập nhật</button>
                  <button class="btn btn-default btn-sm" onclick="location.href='${deleteUrl}'" disabled>Xóa</button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
    </div>
</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

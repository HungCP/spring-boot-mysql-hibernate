<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/15/2015
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
    <meta charset="utf-8">
    <title>Attendance</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <sec:authorize access="hasAnyAuthority('GIAO_VIEN','ADMIN')">
            <li><a href="/courseAttendance/create/${model.course.id}">Tạo buổi học</a></li>
        </sec:authorize>
      </ul>
    </nav>

    <div class="container">

        <h1>${model.course.ma} - ${model.course.name}</h1>

        <h2>Giáo Viên Chủ Nhiệm: ${model.giaoVien.name}</h2>

        <table class="table table-bordered table-hover span12">
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
                    <td>${sinhVien.ma}</td>
                    <td>${sinhVien.lastName}</td>
                    <td>${sinhVien.firstName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <h1>Danh sách buổi học</h1>

        <table class="table table-bordered table-hover span12">
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
                    <td><a href="/courseAttendance/${courseAttendance.id}">${courseAttendance.name}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>
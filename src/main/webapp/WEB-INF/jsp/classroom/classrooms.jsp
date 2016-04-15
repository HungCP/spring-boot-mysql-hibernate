<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/14/2015
  Time: 10:27 AM
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
  <title>List of Classrooms</title>
</head>

<body>

  <nav role="navigation">
    <ul>
      <li><a href="/">Home</a></li>
      <li><a href="/classroom/create">Tạo phòng học mới</a></li>
    </ul>
  </nav>

  <div class="container">
    <h1>Danh sách phòng học</h1>

    <table class="table table-bordered table-hover span12">
      <thead>
      <tr>
        <th>Mã phòng</th>
        <th>Tên phòng</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
        <c:forEach items="${classrooms}" var="classroom" varStatus="status">
          <tr>
            <td><a href="/classroom/${classroom.id}/update">${classroom.ma}</a></td>
            <td>${classroom.name}</td>
            <td>
              <spring:url value="/classroom/${classroom.id}/delete" var="deleteUrl" />
              <spring:url value="/classroom/${classroom.id}/update" var="updateUrl" />

              <button class="btn btn-primary btn-sm" onclick="location.href='${updateUrl}'">Cập nhật</button>
              <button class="btn btn-default btn-sm" onclick="location.href='${deleteUrl}'">Xóa</button>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

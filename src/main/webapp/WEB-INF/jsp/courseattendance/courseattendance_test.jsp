<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 04/22/2016
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
  String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
  <meta charset="utf-8">
  <title>Test</title>

  <meta name="_csrf" content="${_csrf.token}"/>
  <!-- default header name is X-CSRF-TOKEN -->
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
  <nav role="navigation">
    <ul>
      <li><a href="/">Home</a></li>
      <li><a href="/courses">Danh sách lớp học</a></li>
    </ul>
  </nav>

  <div class="container">
    <h1>Danh sách sinh viên tham gia buổi học</h1>
    <br>
    <h1>Danh sách hình ảnh điểm danh</h1>
  </div>

  <div class="container-fluid">
    <div class="table-responsive">
      <table class="table table-bordered table-hover span12" border="1">
        <thead>
        <tr>
          <th class="col-sm-9">Hình ảnh</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${model.imagesFaceList}" var="image" varStatus="status">
          <tr>
            <td>
              <div class="form-group">
                <img id="cropbox" src="data:image/jpeg;base64,${image['key']}" title="${image['value']}" />
                <label class="text-info">${image['value']}</label>
              </div>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

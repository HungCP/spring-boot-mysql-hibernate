<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 04/14/2016
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
    <meta charset="utf-8">
    <title>Create a new classroom</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
      </ul>
    </nav>

    <div class="container">

      <c:choose>
        <c:when test="${form['new']}">
          <h1>Thêm mới phòng học</h1>
        </c:when>
        <c:otherwise>
          <h1>Cập nhật phòng học</h1>
        </c:otherwise>
      </c:choose>
      <br />

      <form:form class="form-horizontal" method="post" modelAttribute="form" action="">

        <form:hidden path="id" />

        <spring:bind path="ma">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Mã</label>
            <div class="col-sm-10">
              <form:input path="ma" type="text" class="form-control " id="ma" placeholder="Nhập mã" />
              <form:errors path="ma" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <spring:bind path="name">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Tên phòng</label>
            <div class="col-sm-10">
              <form:input path="name" type="text" class="form-control " id="name" placeholder="Nhập tên phòng" />
              <form:errors path="name" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <c:choose>
              <c:when test="${form['new']}">
                <button type="submit" class="btn-lg btn-primary pull-right">Thêm mới</button>
              </c:when>
              <c:otherwise>
                <div class="form-actions pull-right">
                  <button type="submit" class="btn btn-primary">Cập nhật</button>
                  <a href="/classrooms" class="btn btn-default">Thoát</a>
                </div>
                <%--<button type="submit" class="btn-lg btn-primary pull-right">Cập nhật</button>--%>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </form:form>

    </div>
</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

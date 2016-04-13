<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 03/21/2016
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="s"   uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

  <head>
      <meta charset="utf-8">
      <title>Tạo mới buổi học</title>
  </head>

  <body>
      <nav role="navigation">
        <ul>
          <li><a href="/">Home</a></li>
        </ul>
      </nav>

      <h1>Tạo mới buổi học</h1>

     <%-- <form role="form" name="model.form" action="" method="post">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

          <div>
              <label for="name">Tiêu đề: </label>
              <input type="text" name="name" id="name" value="${form.name}" required autofocus/>
          </div>

          <div>
              <label for="course">Lớp: </label>
              <input type="text" name="courseName" id="course" value="${form.course.name}" disabled="true"/>
          </div>

          <button type="submit">Save</button>
      </form>--%>

    <div class="container">

      <c:choose>
          <c:when test="${userForm['new']}">
              <h1>Thêm mới người dùng</h1>
          </c:when>
          <c:otherwise>
              <h1>Cập nhật người dùng</h1>
          </c:otherwise>
      </c:choose>
      <br />

      <form:form class="form-horizontal" method="post" modelAttribute="form" action="">

          <form:hidden path="id" />

          <spring:bind path="name">
              <div class="form-group ${status.error ? 'has-error' : ''}">
                  <label class="col-sm-2 control-label">Tiêu đề</label>
                  <div class="col-sm-10">
                      <form:input path="name" type="text" class="form-control " id="name" placeholder="Nhập tiêu đề" />
                      <form:errors path="name" class="control-label" />
                  </div>
              </div>
          </spring:bind>

          <spring:bind path="course">
              <div class="form-group ${status.error ? 'has-error' : ''}">
                  <label class="col-sm-2 control-label">Lớp học</label>
                  <div class="col-sm-10">
                      <form:input path="course" type="text" class="form-control" id="course" value="${form.course.name}" readonly="true"/>
                      <form:errors path="course" class="control-label" />
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
                          <button type="submit" class="btn-lg btn-primary pull-right">Cập nhật</button>
                      </c:otherwise>
                  </c:choose>
              </div>
          </div>
      </form:form>
    </div>

  </body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

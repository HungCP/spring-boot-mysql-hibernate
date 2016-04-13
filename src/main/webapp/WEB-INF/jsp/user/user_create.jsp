<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 04/01/2016
  Time: 4:11 PM
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
  <title>Create a new user</title>

</head>
<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
      </ul>
    </nav>

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

      <form:form class="form-horizontal" method="post" modelAttribute="userForm" action="">

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

        <spring:bind path="email">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
              <form:input path="email" class="form-control" id="email" placeholder="Email" />
              <form:errors path="email" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <spring:bind path="passwordHash">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Mật khẩu</label>
            <div class="col-sm-10">
              <form:password path="passwordHash" class="form-control" id="passwordHash" placeholder="Mật khẩu" showPassword="true"/>
              <form:errors path="passwordHash" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Xác nhận mật khẩu</label>
            <div class="col-sm-10">
              <form:password path="confirmPassword" class="form-control" id="passwordHash" placeholder="Xác nhận mật khẩu" showPassword="true"/>
              <form:errors path="confirmPassword" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <spring:bind path="lastName">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Họ</label>
            <div class="col-sm-10">
              <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Họ" />
              <form:errors path="lastName" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <spring:bind path="firstName">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Tên</label>
            <div class="col-sm-10">
              <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="Tên" />
              <form:errors path="firstName" class="control-label" />
            </div>
          </div>
        </spring:bind>

        <spring:bind path="role">
          <div class="form-group ${status.error ? 'has-error' : ''}">
            <label class="col-sm-2 control-label">Vai trò</label>
            <div class="col-sm-10">
                <select id="role" name='<c:out value="${status.expression}"/>'>
                    <c:forEach var="option" items="${userForm.allRole}">
                        <option value="<c:out value="${option}"/>" <c:if test="${ option == status.value}">selected</c:if>>
                            <c:out value="${option.text}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
          </div>
        </spring:bind>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <c:choose>
              <c:when test="${userForm['new']}">
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

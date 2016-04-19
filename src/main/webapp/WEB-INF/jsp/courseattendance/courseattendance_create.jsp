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

<%
    String contextPath = request.getContextPath();
%>

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

    <div class="container">
        <c:choose>
            <c:when test="${model.form['new']}">
                <h1>Thêm buổi học mới</h1>
            </c:when>
            <c:otherwise>
                <h1>Cập nhật buổi học</h1>
            </c:otherwise>
        </c:choose>
        <br/>
        <form:form class="form-horizontal" method="post" modelAttribute="model" action="" >

            <form:hidden path="form.id"/>

            <spring:bind path="form.name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Tiêu đề</label>
                    <div class="col-sm-10">
                        <form:input path="name" type="text" class="form-control " id="name" placeholder="Nhập tiêu đề"/>
                        <form:errors path="name" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="form.course">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Lớp học</label>
                    <div class="col-sm-10">
                        <form:input path="course" class="form-control" id="course" disabled="true"/>
                        <form:errors path="course" class="control-label"/>
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${coursemodel.form['new']}">
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
</div>

</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>

<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 03/21/2016
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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

      <form role="form" name="form" action="" method="post">
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
      </form>

  </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 03/25/2016
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CourseAttendence details</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/course/${course.id}">${course.ma}</a></li>
      </ul>
    </nav>

    <h1>${model.course.ma} - ${model.course.name}</h1>

    <h1>${model.courseAttendence.name}</h1>

</body>
</html>

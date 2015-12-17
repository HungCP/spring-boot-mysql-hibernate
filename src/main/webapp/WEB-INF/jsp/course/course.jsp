<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/15/2015
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <style>
      table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
      }
      th, td {
        padding: 5px;
        text-align: left;
      }
    </style>
    <title>Course details</title>
</head>

<body>
    <nav role="navigation">
      <ul>
        <li><a href="/">Home</a></li>
      </ul>
    </nav>

    <h1>${model.course.ma} - ${model.course.name}</h1>



</body>
</html>

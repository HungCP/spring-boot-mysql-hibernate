<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/04/2015
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    String contextPath = request.getContextPath();
%>
<head>
    <link href=<%=contextPath%>"/css/bootstrap.min.css" rel="stylesheet"/>
    <script src=<%=contextPath%>"/js/jquery.min.js"></script>
    <script src=<%=contextPath%>"/js/bootstrap.min.js"></script>
</head>

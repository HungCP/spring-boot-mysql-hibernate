<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/04/2015
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Home Page</title>
  </head>

  <body>

  <jsp:include page="fragments/_header.jsp"></jsp:include>
  <jsp:include page="fragments/_menu.jsp"></jsp:include>

  <h3>Home Page</h3>

  This is Simple web application<br><br>

  Hello <b>${loginedUser.name}</b>

  <form th:action="@{/logout}" method="post">
    <input type="submit" value="Sign Out"/>
  </form>

  <jsp:include page="fragments/_footer.jsp"></jsp:include>

  </body>

</html>

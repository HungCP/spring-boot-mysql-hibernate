<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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

<h1>Create a new user</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div>
        <label for="lastName">Họ</label>
        <input type="text" name="lastName" id="lastName" value="${form.lastName}" required autofocus/>
    </div>

    <div>
        <label for="firstName">Tên</label>
        <input type="text" name="firstName" id="firstName" value="${form.firstName}" required autofocus/>
    </div>

    <div>
        <label for="email">Email address</label>
        <input type="email" name="email" id="email" value="${form.email}" required autofocus/>
    </div>
    
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>

    <div>
        <label for="passwordRepeated">Repeat</label>
        <input type="password" name="passwordRepeated" id="passwordRepeated" required/>
    </div>

    <div>
        <label for="role">Vai trò</label>
        <select name="role" id="role" required>
            <option value=""></option>
            <c:forEach items="${form.allRole}" var="option">
                <option value="${option}">
                    <c:out value="${option.text}"></c:out>
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit">Save</button>
</form>

</body>
</html>
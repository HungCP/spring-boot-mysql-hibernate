<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/_header.jsp"></jsp:include>

<head>
	<meta charset="utf-8">
	<title>Login</title>
</head>

<body>

	<div class="container">
		
		<form class="form-signin" role="form" th:action="@{/login}" method="POST">

			<p>You can use: demo@localhost / demo</p>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

			<h2 class="form-signin-heading">Please sign in</h2>

			<label for="email" class="sr-only">Email address</label>
			<input
				type="email" id="email" name="email" value="demo@localhost"
				class="form-control" placeholder="Email address" required autofocus>

			<label
				for="password" class="sr-only">Password</label> 
			<input
				type="password" id="password" name="password"
				class="form-control" placeholder="Password" value="demo" required>

			<div class="checkbox">
				<label> 
					<input type="checkbox" name="remember-me" id="remember-me" value="remember-me">
					Remember me
				</label>
			</div>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

			<c:if test="${error != null}">
			    <p>The email or password you have entered is invalid, try again.</p>
			</c:if>
		</form>
		
	</div>

</body>

<jsp:include page="fragments/_footer.jsp"></jsp:include>

</html>
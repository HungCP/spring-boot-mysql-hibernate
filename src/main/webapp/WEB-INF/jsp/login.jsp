<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in</title>
    <link href="css/bootstrap.css" rel="stylesheet"></link>
    <script src="js/jquery-2.2.0.js" ></script>
    <script src="js/bootstrap.js" ></script>
	<style type="text/css">
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
		}
		
		.form-signin {
			max-width: 330px;
			padding: 15px;
			margin: 0 auto;
		}
		
		.form-signin .form-signin-heading, .form-signin .checkbox {
			margin-bottom: 10px;
		}
		
		.form-signin .checkbox {
			font-weight: normal;
		}
		
		.form-signin .form-control {
			position: relative;
			height: auto;
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
			padding: 10px;
			font-size: 16px;
		}
		
		.form-signin .form-control:focus {
			z-index: 2;
		}
		
		.form-signin input[type="email"] {
			margin-bottom: -1px;
			border-bottom-right-radius: 0;
			border-bottom-left-radius: 0;
		}
		
		.form-signin input[type="password"] {
			margin-bottom: 10px;
			border-top-left-radius: 0;
			border-top-right-radius: 0;
		}
	</style>
</head>
<body>
	<!-- <nav role="navigation">
		<ul>
			<li><a href="/">Home</a></li>
		</ul>
	</nav> -->
	
	<div class="container">
		
		<form class="form-signin" role="form" action="/login" method="post">
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
				class="form-control" placeholder="Password" value="demo@localhost" required>
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
</html>
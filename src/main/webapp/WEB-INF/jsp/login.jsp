<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Recommendation Engine</title>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%> 
<script>
     history.forward();
</script> 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css">
	<%@ include file = "css/loginstyle.css" %>
</style>
<script type="text/javascript">
	<%@ include file = "js/validation.js" %>	
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Payments</a>
			</div>
		</div>
	</nav>
	<div class="error">
		<%
			if (request.getAttribute("LoginError") != null) {
		%>
		<font color="red"> ${LoginError}</font>
		<%
			}
		%>
	</div>
	<div class="login">
			<h2>Sign in</h2>
			<div class="heading">
			<form action="/accountsignup" method="POST" onsubmit="return loginValidation()" name = "login-form">
				<div class="inputs">
				<div class="input-group input-group-lg">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" class="form-control" placeholder="Username" name = "username">
				</div>

				<div class="input-group input-group-lg">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" placeholder="Password"  name = "password">
				</div>
				</div>
				<div class = "login-button">
				<button type="submit" class="float">Login</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
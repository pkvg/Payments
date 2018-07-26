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
<%@ include file = "css/accountstyle.css" %>
</style> 
<script type="text/javascript">
<%@ include file = "js/validation.js" %>
</script>
</head>
<body>
	<div class="account-status">
		<%
			if (request.getAttribute("accountStatus") != null) {
		%>
		<font color="red"> ${accountStatus}</font>
		<%
			}
		%>
	</div>
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
				<a class="navbar-brand" href="/logout" style="position: relative;left: 950px;">Log out</a>	
			</div>
		</div>
	</nav>
	<div class="container">
		<h1 class="page-header">Account SignUp</h1>
		<form name="accountsignup-form" class="form-inline" action="/accountcreation"
			onsubmit="return amountValidation()" method="POST">
			<div class="form-group">
				<input type="text" class="form-control" name="amount"
					placeholder="Enter Amount">
			</div>
			<button type="submit" class="btn btn-primary">Create Account</button>
		</form>
	</div>
	<div class="transfer">
		<form name="transfer-form" class="form-inline" action="/transfer"
			method="GET">
			<button type="submit" class="btn btn-primary">Transfer</button>
		</form>
	</div>
	
</body>
</html>
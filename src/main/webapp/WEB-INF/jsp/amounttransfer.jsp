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
	<div class="amount-transfer-status">
		<%
			if (request.getAttribute("amounttransferstatus") != null) {
		%>
		<font color="red"> ${amounttransferstatus}</font>
		<%
			}
		%>
	</div>
	<div class="amount-in-negative">
		<%
			if (request.getAttribute("amountinnegative") != null) {
		%>
		<font color="red"> ${amountinnegative}</font>
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
				<a class="navbar-brand" href="/home" style="position: relative;left: 50px;">Home</a>
				<a class="navbar-brand" href="/logout" style="position: relative;left: 950px;">Log out</a>					
			</div>
		</div>
	</nav>
	<div class="container">
		<h1 class="page-header">Amount Transfer</h1>
		<form name="transfer-form" class="form-inline" action="/amounttransaction" method="POST"
		onsubmit="return amountTransferValidation()">
			<div class="form-group">
				<input type="text" class="form-control" name="fromaccountid"
					placeholder="From Account Id">
				<input type="text" class="form-control" name="toaccountid"
					placeholder="To Account Id">
				<input type="text" class="form-control" name="transferamount"
					placeholder="Amount">	
			</div>
			<button type="submit" class="btn btn-primary">Transfer Amount</button>
		</form>
	</div>
	
	<div class="container">
		<h1 class="page-header">View Balance</h1>
		<form name="view-balance-form" class="form-inline" action="/viewbalance" method="GET" 
		onsubmit="return viewBalanceValidation()">
			<div class="form-group">
				<input type="text" class="form-control" name="accountid"
					placeholder="AccountId">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class = "amount-display">
	<%
			if (request.getAttribute("amount") != null) {
		%>
	<h3> Your Account Balance is ${amount} Euros</h3>
	<%
			}
		%>
	</div>
</body>
</html>
function amountValidation() {
	var amount = document.forms["accountsignup-form"]["amount"];
	if (amount.value == "") {
		alert("Please enter Amount");
		amount.focus();
		return false;
	}

	if (amount.value <= 10) {
		alert("Minimum amount should be more than 10 Euros");
		amount.focus();
		return false;
	}
}

function loginValidation() {
	var username = document.forms["login-form"]["username"];
	var password = document.forms["login-form"]["password"];
	if (username.value == "" || password.value == "") {
		alert("Please fill the fields");
		username.focus();
		return false;
	}
}
function amountTransferValidation()
{
	var fromAccountId = document.forms["transfer-form"]["fromaccountid"];
	var toAccountId = document.forms["transfer-form"]["toaccountid"];
	var transferAmount = document.forms["transfer-form"]["transferamount"];
	if (fromAccountId.value == "" || toAccountId.value == "" ||transferAmount.value == "") {
		alert("Please fill all the required fields");
		fromAccountId.focus();
		return false;
	}
	if (transferAmount.value == "0") {
		alert("Please provide a valid amount");
		transferAmount.focus();
		return false;
	}

}
function viewBalanceValidation()
{
	var accountId = document.forms["view-balance-form"]["accountid"];
	if (accountId.value == ""){
		alert("Please provide account id");
		accountId.focus();
		return false;
	}
}


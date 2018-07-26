package com.test.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.web.service.AccountService;

@Controller
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/frontpage", method = RequestMethod.GET)
	public String showAccountSignUpPage() {
		return "accountsignup";
	}

	@RequestMapping(value = "/accountcreation", method = RequestMethod.POST)
	public String createAccount(@RequestParam("amount") String amount, Model model) {
		logger.info("Amount... " + amount );
		accountService.insertAmountData(amount);
		model.addAttribute("accountStatus", "Account created successfully");
		return "accountsignup";

	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public String showAmountTransferPage() {
		return "amounttransfer";
	}

	@RequestMapping(value = "/amounttransaction", method = RequestMethod.POST)
	public String fundTransfer(@RequestParam("fromaccountid") String fromAccountId,
			@RequestParam("toaccountid") String toAccountId, @RequestParam("transferamount") String amount,
			Model model) {
		if (accountService.transferAmounttoAccount(fromAccountId, toAccountId, amount))
			model.addAttribute("amounttransferstatus", "Amount Transfered Succesfully");
		else
			model.addAttribute("amountinnegative", "Please check for sufficient funds in the account to transfer");
		return "amounttransfer";

	}

	@RequestMapping(value = "/viewbalance", method = RequestMethod.GET)
	public String viewBalance(@RequestParam("accountid") String accountid, Model model) {
		String amount = accountService.findBalance(accountid);
		model.addAttribute("amount", amount);
		return "amounttransfer";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage() {
		return "accountsignup";
	}
}

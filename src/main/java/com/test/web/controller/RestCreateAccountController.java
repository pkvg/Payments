package com.test.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.web.errmsg.CustomMessage;
import com.test.web.model.Account;
import com.test.web.model.AmountTransferDetails;
import com.test.web.service.AccountService;

@RestController
public class RestCreateAccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		accountService.r_createAccount(account);
		// return ResponseEntity.status(HttpStatus.OK).build();
		return new ResponseEntity(new CustomMessage("Sucessfully created account."), HttpStatus.OK);
	}

	@RequestMapping(value = "/transferamount", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> fundTransfer(@RequestBody AmountTransferDetails amountTransferDetail) {
		String transferMessage = accountService.r_transferAmount(amountTransferDetail);
		return new ResponseEntity(new CustomMessage(transferMessage), HttpStatus.OK);

	}

}

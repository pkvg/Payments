package com.test.web.service;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.AccountDao;
import com.test.web.model.Account;
import com.test.web.model.AmountTransferDetails;

@Service
public class AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static AtomicLong idCounter = new AtomicLong();

	@Autowired
	private AccountDao accountDao;

	public void insertAmountData(String amount) {
		Account account = new Account();
		String name = "xxx";
		String age = "yyy";
		String accountId = AccountService.createID();
		account.setAccountId(accountId);
		account.setName(name);
		account.setAge(age);
		account.setAmount(amount);
		logger.info("Account Id >>>" + account.getAccountId());
		accountDao.insert(account);

	}
	
	public void r_createAccount(Account account) {
		logger.info("Account Id >>>" + account.getAccountId());
		accountDao.insert(account);
	}

	public static String createID() {
		return String.valueOf(idCounter.getAndIncrement());
	}

	public boolean transferAmounttoAccount(String fromAccountId, String toAccountId, String amount) {
		Account fromAccountData = accountDao.findOne(fromAccountId);
		Account toAccountData = accountDao.findOne(toAccountId);

		logger.info("Before Transaction, Account Balance - From Account Id >>>" + fromAccountData.getAmount());
		logger.info("Before Transaction, Account Balance - To Account Id >>>" + toAccountData.getAmount());

		if (Integer.parseInt(fromAccountData.getAmount()) < 0
				|| Integer.parseInt(fromAccountData.getAmount()) < Integer.parseInt(amount))
			return false;
		else {

			String toAccountDataAmount = String
					.valueOf(Integer.parseInt(toAccountData.getAmount()) + Integer.parseInt(amount));
			String fromAccountDataAmount = String
					.valueOf(Integer.parseInt(fromAccountData.getAmount()) - Integer.parseInt(amount));
			toAccountData.setAmount(toAccountDataAmount);
			fromAccountData.setAmount(fromAccountDataAmount);

			logger.info("After Transaction, Account Balance - From Account Id >>>" + fromAccountData.getAmount());
			logger.info("After Transaction, Account Balance - To Account Id >>>" + toAccountData.getAmount());

			accountDao.insert(fromAccountData);
			accountDao.insert(toAccountData);
			return true;
		}

	}
	
	public String r_transferAmount(AmountTransferDetails amountTransferDetail)
	{
		Account fromAccountData = accountDao.findOne(amountTransferDetail.getFromAccountId());
		Account toAccountData = accountDao.findOne(amountTransferDetail.getToAccountId());

		if (Integer.parseInt(fromAccountData.getAmount()) < 0
				|| Integer.parseInt(fromAccountData.getAmount()) < Integer.parseInt(amountTransferDetail.getAmount()))
			return "Please check for sufficient funds";
		else {

			String toAccountDataAmount = String
					.valueOf(Integer.parseInt(toAccountData.getAmount()) + Integer.parseInt(amountTransferDetail.getAmount()));
			String fromAccountDataAmount = String
					.valueOf(Integer.parseInt(fromAccountData.getAmount()) - Integer.parseInt(amountTransferDetail.getAmount()));
			toAccountData.setAmount(toAccountDataAmount);
			fromAccountData.setAmount(fromAccountDataAmount);

			logger.info("After Transaction, Account Balance - From Account Id >>>" + fromAccountData.getAmount());
			logger.info("After Transaction, Account Balance - To Account Id >>>" + toAccountData.getAmount());

			accountDao.insert(fromAccountData);
			accountDao.insert(toAccountData);
			return "Sucessfully transferred amount";
		}

	}

	public String findBalance(String accountId) {
		Account accountDetails = accountDao.findOne(accountId);
		logger.info("Account Balance >>>" + accountDetails.getAmount());
		return accountDetails.getAmount();
	}

}

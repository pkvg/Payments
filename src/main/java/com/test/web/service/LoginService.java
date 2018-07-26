package com.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.web.dao.LoginDao;
import com.test.web.model.LoginCredentials;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;

	public boolean validateUser(String userName, String password) {
		LoginCredentials loginData = loginDao.findOne(userName);
		if (loginData == null)
			return false;
		else
			return loginData.getPassword().equals(password);
	}

}

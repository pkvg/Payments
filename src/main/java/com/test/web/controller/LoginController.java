package com.test.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.web.service.LoginService;

@Controller
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/accountsignup", method = RequestMethod.POST)
	public String validateLoginCredentials(@RequestParam("username") String userName,
			@RequestParam("password") String password, Model model) {
		if (loginService.validateUser(userName, password)) {
			return "accountsignup";
		} else {
			model.addAttribute("LoginError", "UserName or Password is invalid");
			logger.info("Entered UserName or Password is incorrect");
			return "login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut() {
		return "login";
	}

}

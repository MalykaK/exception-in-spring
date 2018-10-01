package com.capgemini.bank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bank.exceptions.AccountIdNotFoundException;
import com.capgemini.bank.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = UserNotFoundException.class)
	public String handleError(HttpServletRequest request, UserNotFoundException exception) {
		System.out.println(exception);
		System.out.println(exception.getCause());
		request.setAttribute("errorname", exception);
		request.setAttribute("cause", exception.getCause());
		exception.printStackTrace();
		return "err";
	}

	@ExceptionHandler(value = AccountIdNotFoundException.class)
	public String handleAccountNotFoundException(HttpServletRequest request, AccountIdNotFoundException exception) {
		System.out.println(exception);
		System.out.println(exception.getCause());
		request.setAttribute("errorname", exception);
		request.setAttribute("cause", exception.getCause());
		exception.printStackTrace();
		return "err";
	}
}

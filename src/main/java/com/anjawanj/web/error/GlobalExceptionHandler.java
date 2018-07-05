package com.anjawanj.web.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.anjawanj.web.controller.ReservationServiceController;


@ControllerAdvice(basePackageClasses = ReservationServiceController.class)
public class GlobalExceptionHandler {
	
	@Value("${error.bad.guest.email.id}")
	private String guestNotFoundMessage;
	
	@Value("${error.registration.failure}")
	private String registrationFailureMessage;
	
	@ExceptionHandler(GuestNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo guestNotFound(HttpServletRequest req, GuestNotFoundException ex) {
		String errorMessage = guestNotFoundMessage+ex.getEmailID();
	        String errorURL = req.getRequestURL().toString();
	         
	        return new ErrorInfo(errorURL, errorMessage);
	}
	
	@ExceptionHandler(CustomerRegistrationException.class)
	@ResponseStatus(value=HttpStatus.NOT_MODIFIED)
	@ResponseBody
	public ErrorInfo smartphoneNotFound(HttpServletRequest req, CustomerRegistrationException ex) {
		String errorMessage = registrationFailureMessage + ex.getMessage();
	        String errorURL = req.getRequestURL().toString();
	         
	        return new ErrorInfo(errorURL, errorMessage);
	}
}

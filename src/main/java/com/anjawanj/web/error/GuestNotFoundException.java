package com.anjawanj.web.error;

public class GuestNotFoundException extends RuntimeException 
{

private final String emailID;
public String getEmailID() {
	return emailID;
}

public GuestNotFoundException(String emailID) {

	this.emailID = emailID;
}
	
private static final long serialVersionUID = 100L;

}
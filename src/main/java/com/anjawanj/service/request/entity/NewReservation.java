package com.anjawanj.service.request.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class NewReservation implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1990L;
	
    
    private List<String> emailAddresses;
    
    private long noOfGuests;
    
    private Date startDate;
    
    private Date endDate;

	public List<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddress(List<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public long getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(long noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    
    
}

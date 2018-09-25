package com.anjawanj.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.persistence.JoinColumn;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="RESERVATION")
public class Reservation {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RESERVATION_ID")
    private long id;
    
    @Column(name="RES_START_DATE")
    @Future
    private Date startDate;
    
    @Column(name="RES_END_DATE")
    @Future
    private Date endDate;
    
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="Guest_Reservations",
    	joinColumns= {@JoinColumn(name="RESERVATION_ID")},
    inverseJoinColumns= {@JoinColumn(name="GUEST_ID")})
    private List<Guest> guests = new ArrayList<Guest>();
    
    public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	@Column(name="ROOM_ID")
    private long roomId;
    
    @Column(name="NO_OF_GUESTS")
    private long noOfGuests;    
   
	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
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

	
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  
    
}
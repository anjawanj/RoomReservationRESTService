package com.anjawanj.business.service;

import com.anjawanj.data.repository.GuestRepository;
import com.anjawanj.data.repository.ReservationRepository;
import com.anjawanj.data.repository.RoomRepository;
import com.anjawanj.persistence.model.Guest;
import com.anjawanj.persistence.model.Reservation;
import com.anjawanj.persistence.model.Room;
import com.anjawanj.service.request.entity.NewReservation;
import com.anjawanj.web.error.CustomerRegistrationException;
import com.anjawanj.web.error.GuestNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.List;

@Service
public class ReservationService {
	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private ReservationRepository reservationRepository;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
			ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	public List<Reservation> getRoomReservationsForDate(String dateString) {

		Date date = this.createDateFromDateString(dateString);
		List<Reservation> reservationsForTheDay = this.reservationRepository
				.findByStartDate(new java.sql.Date(date.getTime()));

		return reservationsForTheDay;

	}

	public Page<Guest> getAllGuests(int pageNumber, int pageSize) {

		return guestRepository.findAll(new PageRequest(pageNumber, pageSize));
	}

	public Page<Reservation> getPage(int pageNumber, int pageSize) {

		return reservationRepository.findAll(new PageRequest(pageNumber, pageSize));
	}

	public Reservation getReservationForRoom(String roomID) {
		Long roomID1 = Long.parseLong(roomID);
		Reservation reservation = this.reservationRepository.findByRoomId(roomID1);
		return reservation;
	}

	public void reserveRoomForCustomer(NewReservation newReservation) {

		List<Guest> guests = new ArrayList<Guest>();

		for (String emailID : newReservation.getEmailAddresses()) {
			guests.add(guestRepository.findByEmailAddress(emailID));
		}

		// get available room
		Room room = roomRepository.getAvailableRoom();

		Reservation reservation = new Reservation();
		reservation.setStartDate(newReservation.getStartDate());
		reservation.setEndDate(newReservation.getEndDate());
		reservation.setNoOfGuests(guests.size());
		reservation.setRoomId(room.getId());
		reservation.setGuests(guests);

		this.reservationRepository.save(reservation);

		room.setReserved(true);
		this.roomRepository.save(room);

	}

	public void registerCustomer(Guest guest) {

		Guest savedGuest = this.guestRepository.save(guest);

		if (null == savedGuest)
			throw new CustomerRegistrationException();
	}

	private Date createDateFromDateString(String dateString) {
		Date date = null;
		if (null != dateString) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (ParseException pe) {
				date = new Date();
			}
		} else {
			date = new Date();
		}
		return date;
	}

	public Guest getGuestInfo(String emailID) {

		Guest guest = guestRepository.findByEmailAddress(emailID);

		if (null != guest)
			return guest;
		else {
			throw new GuestNotFoundException(emailID);
		}

	}
}

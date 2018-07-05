package com.anjawanj.web.controller;

import com.anjawanj.business.service.ReservationService;
import com.anjawanj.persistence.model.Guest;
import com.anjawanj.persistence.model.Reservation;
import com.anjawanj.service.request.entity.NewReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reservation/api/v1/reservations")
public class ReservationServiceController {

	@Autowired
	private ReservationService reservationService;
	
	//Get all reservations 
	//Use Paging to get limited results: e.g. /cars?offset=10&limit=5
	@RequestMapping(method = RequestMethod.GET)
	public List<Reservation> getAllReservations(@RequestParam(defaultValue = "1") int pageNumber,@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(value = "date", required=false) String dateString){
		List<Reservation> reservation = null;
		
		if(null != dateString) {
			reservation = this.reservationService.getRoomReservationsForDate(dateString);
			return reservation;
		}else {
			final Page<Reservation> resultPage = reservationService.getPage(pageNumber,pageSize);
			return resultPage.getContent();
		}			 
	}

	/*//Filtering example
	//e.g. GET /cars?color=red Returns a list of red cars
	//Here we are passing date to filter reservations for particular date
	@RequestMapping(method = RequestMethod.GET)
	public List<Reservation> getAllReservationsForDate(@RequestParam(value = "date") String dateString) {
		return this.reservationService.getRoomReservationsForDate(dateString);
	}*/

	//Update reservation
	//@RequestMapping(method = RequestMethod.PUT, value = "/reservations")
	
	//delete all reservations
	//@RequestMapping(method = RequestMethod.DELETE, value = "/reservations")
	
	//Create new reservation
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> reserveRoomForCustomer(@Valid @RequestBody NewReservation reservation) {

		this.reservationService.reserveRoomForCustomer(reservation);
		return null;

	}
	

	//Get Particular reservation
	//@RequestMapping(method = RequestMethod.GET, value = "/reservations/{id}")
	
	//Method Not Allowed
		//@RequestMapping(method = RequestMethod.POST, value = "/reservations/{id}")
	
	//update specific reservation
		//@RequestMapping(method = RequestMethod.PUT, value = "/reservations/{id}")
	
	//delete specific reservation
		//@RequestMapping(method = RequestMethod.DELETE, value = "/reservations/{id}")
	
		//If a resource is related to another resource use subresources.
		@RequestMapping(method = RequestMethod.GET, value = "/rooms/{room_id}")
		public Reservation getReservationForRoom(@PathVariable(value = "room_id") String roomId) {
			return this.reservationService.getReservationForRoom(roomId);
		}
	
	

}

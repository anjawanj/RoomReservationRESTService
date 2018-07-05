package com.anjawanj.web.controller;

import com.anjawanj.business.service.ReservationService;
import com.anjawanj.persistence.model.Guest;
import com.anjawanj.persistence.model.Reservation;
import com.anjawanj.service.request.entity.NewReservation;
import com.anjawanj.web.hateoas.event.PaginatedResultsRetrievedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reservation/api/v1/guests")
public class GuestServiceController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
		
	
		//Get all Guests 
		//Use Paging to get limited results: e.g. /cars?offset=10&limit=5
		@RequestMapping(method = RequestMethod.GET)
		public List<Guest> getAllGuests(@RequestParam(defaultValue = "1") int pageNumber,@RequestParam(defaultValue = "10") int pageSize,
				UriComponentsBuilder uriBuilder, HttpServletResponse response){
			
				final Page<Guest> resultPage = reservationService.getAllGuests(pageNumber, pageSize);
				
				
				eventPublisher.publishEvent( new PaginatedResultsRetrievedEvent<Guest>
			    ( Guest.class, uriBuilder, response, pageNumber, resultPage.getTotalPages(), pageSize ) );
				
				
				return resultPage.getContent();
				 
		}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	@ResponseBody
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody Guest guest, UriComponentsBuilder ucBuilder,
			HttpServletRequest request) {
		this.reservationService.registerCustomer(guest);

		// ucBuilder.queryParam(name, values)
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/guests/{id}").buildAndExpand(guest.getId()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{email_id:.+}")
	@ResponseStatus(HttpStatus.FOUND)
	public Guest getGuestInfo(@PathVariable(value = "email_id") String emailID) {

		Guest guest = this.reservationService.getGuestInfo(emailID);
		return guest;
	}

}

package com.anjawanj.web.controller;

import com.anjawanj.business.service.ReservationService;
import com.anjawanj.persistence.model.Guest;
import com.anjawanj.persistence.model.Reservation;
import com.anjawanj.service.request.entity.NewReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reservation/api/v1/rooms")
public class RoomServiceController {

	@Autowired
	private ReservationService reservationService;


	



}

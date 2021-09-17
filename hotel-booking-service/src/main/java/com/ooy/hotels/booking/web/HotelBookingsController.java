package com.ooy.hotels.booking.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ooy.hotels.booking.bean.BookingDTO;
import com.ooy.hotels.booking.bean.HotelDetailsDTO;
import com.ooy.hotels.booking.bean.ResponseDTO;
import com.ooy.hotels.booking.proxy.feign.HotelListProxy;
import com.ooy.hotels.booking.service.BookingService;

/**
 * REST controller for hotel booking operations
 */
@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class HotelBookingsController {

	@Autowired
	HotelListProxy proxy;
	
	@Autowired
	BookingService service;

	/**
	 * Get all hotels
	 * @return
	 */
	@GetMapping("/findAll")
	//@HystrixCommand(fallbackMethod = "fallbackGetBookings")
	public ResponseEntity<List<BookingDTO>> getBookings() {
		return new ResponseEntity<List<BookingDTO>>(service.getHotelRecords(), HttpStatus.OK);
	}
	
	/**
	 * Get hotel by id
	 * @return
	 */
	@GetMapping("/hotel/findById/{id}")
	//@HystrixCommand(fallbackMethod = "fallBackHotelById")
	public ResponseEntity<HotelDetailsDTO> getHotelRecord(@PathVariable("id") int id) {
		//return new ResponseEntity<HotelDetailsDTO>(proxy.findById(id), HttpStatus.OK);
		return proxy.findById(id);
	}
	
	/**
	 * Create hotel record in db
	 * @param detailsDTO
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping("/create")
	//@HystrixCommand(fallbackMethod = "fallBackCreateBooking")
	public ResponseEntity<ResponseDTO> createHotelRecord(@RequestBody(required = true) BookingDTO detailsDTO) {
		ResponseDTO response = new ResponseDTO();
		service.createUser(detailsDTO);
		response.setMessage("Booking Record Created");
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<List<BookingDTO>> fallbackGetBookings(){
		return new ResponseEntity<List<BookingDTO>>(new ArrayList<BookingDTO>(), HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseDTO> fallBackCreateBooking(BookingDTO detailsDTO){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(), HttpStatus.OK);
	}
	
	public ResponseEntity<HotelDetailsDTO> fallBackHotelById(int id){
		return new ResponseEntity<HotelDetailsDTO>(new HotelDetailsDTO(), HttpStatus.OK);
	}
		
}

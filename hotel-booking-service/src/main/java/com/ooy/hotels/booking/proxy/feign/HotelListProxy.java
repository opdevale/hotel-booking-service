package com.ooy.hotels.booking.proxy.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ooy.hotels.booking.bean.HotelDetailsDTO;

@FeignClient(name = "HotelBooking", url = "http://localhost:8082/hotelRegistration")
public interface HotelListProxy {
	
	@RequestMapping(value = "/hotel/findAll", method = RequestMethod.GET)
    ResponseEntity<List<HotelDetailsDTO>> findAll();	
	
	@RequestMapping(value = "/hotel/findById/{id}", method = RequestMethod.GET)
    ResponseEntity<HotelDetailsDTO> findById(@PathVariable("id") int id);	

}

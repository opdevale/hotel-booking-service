package com.ooy.hotels.booking.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ooy.hotels.booking.bean.BookingDTO;
import com.ooy.hotels.booking.bean.HotelDetailsDTO;
import com.ooy.hotels.booking.entity.Booking;
import com.ooy.hotels.booking.proxy.feign.HotelListProxy;
import com.ooy.hotels.booking.repository.BookingsRepositiry;

/**
 * Business logic to fetch and save hotel details data from database and perform business logic.
 * @author Onkar
 *
 */
@Service
public class BookingService {

	@Autowired
	BookingsRepositiry repo;
	
	@Autowired
	HotelListProxy proxy;
	
	/**
	 * Get all hotels
	 * @return List<HotelDetailsDTO>
	 */
	public List<BookingDTO> getHotelRecords(){
		List<Booking> findAll = repo.findAll();
		List<BookingDTO> list = new ArrayList<>();
		
		findAll.stream().forEach(x -> {
			BookingDTO dto = new BookingDTO();
			dto.setUsername(x.getUsername());
			dto.setEmail(x.getEmail());
			dto.setPhone(x.getPhone());
			dto.setFromdate(x.getFromdate());
			dto.setTodate(x.getTodate());
			dto.setAddress(x.getAddress());
			
			ResponseEntity<HotelDetailsDTO> hotelname = proxy.findById(x.getHotelid());
			dto.setHotelname(hotelname.getBody()!=null? hotelname.getBody().getName():null);
			list.add(dto);
		});
		
		return list;
	}
	
	/**
	 * Find by id hotel details record
	 * @param bookingId
	 * @return HotelDetailsDTO
	 */
	/*public HotelDetailsDTO getHotelRecordById(int bookingId){
		Optional<Hoteldetails> hotelDetail = repo.findById(bookingId);
		HotelDetailsDTO dto = new HotelDetailsDTO();
		
		if(hotelDetail.isPresent()) {
			dto.setName(hotelDetail.get().getName());
			dto.setDescription(hotelDetail.get().getDescription());
			String[] days = hotelDetail.get().getDuration().split("_");
			dto.setDurationDays(Integer.valueOf(days[0]));
			dto.setDurationNights(Integer.valueOf(days[1]));
			dto.setValidity(hotelDetail.get().getValidity());
			dto.setPricePerDay(hotelDetail.get().getPrice());
		} else {
			return null;
		}
			
		return dto;
	}*/
	
	/**
	 * Create hotel record
	 * @param hotelDetailsDTO
	 * @throws ParseException 
	 */
	public void createUser(BookingDTO bookingDTO) {
	
	    	Booking bookingEntity = new Booking();
	    	bookingEntity.setUsername(bookingDTO.getUsername());
			bookingEntity.setEmail(bookingDTO.getEmail());
			bookingEntity.setPhone(bookingDTO.getPhone());
			bookingEntity.setFromdate(bookingDTO.getFromdate());
			bookingEntity.setTodate(bookingDTO.getTodate());
			bookingEntity.setAddress(bookingDTO.getAddress());
			bookingEntity.setHotelid(bookingDTO.getHotelid());
	    	repo.save(bookingEntity);
	    	
	    	List<Booking> findAll = repo.findAll();
	    	
	    	System.out.println(findAll);
	}
	
	/**
	 * Update hotel record
	 * @param hotelDetailsDTO
	 */
	/*public void updateUser(HotelDetailsDTO hotelDetailsDTO) {
	    Optional<Hoteldetails> hotelDetail = repo.findById(hotelDetailsDTO.getId());
	    
	    if(hotelDetail.isPresent()) {
	    	hotelDetail.get().setName(hotelDetailsDTO.getName()); 
	    	hotelDetail.get().setDescription(hotelDetailsDTO.getDescription());
	    	hotelDetail.get().setPrice(hotelDetailsDTO.getPricePerDay());
			hotelDetail.get().setValidity(hotelDetailsDTO.getValidity());
	    	hotelDetail.get().setDuration(hotelDetailsDTO.getDurationDays()+"_"+hotelDetailsDTO.getDurationNights());
	    	repo.save(hotelDetail.get());
	    }
	}*/
	
	/**
	 * Delere hotels by hotel id list
	 * @param list
	 */
	public void deleteHotelRecords(List<Integer> list) {
		list.forEach(element -> {repo.deleteById(element); });
	}
}

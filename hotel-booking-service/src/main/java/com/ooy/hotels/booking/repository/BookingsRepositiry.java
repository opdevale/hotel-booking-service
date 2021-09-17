package com.ooy.hotels.booking.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ooy.hotels.booking.entity.Booking;

/**
 * This interface connects with database table and perform operations like read, write, update and delete.
 * @author Onkar
 *
 */
@Repository
public interface BookingsRepositiry extends CrudRepository<Booking, Integer> {

	List<Booking> findAll();
	
	Optional<Booking> findById(Integer bookingId);
	
	Booking save(Booking entity);
	
	void deleteById(Integer id);
	
}

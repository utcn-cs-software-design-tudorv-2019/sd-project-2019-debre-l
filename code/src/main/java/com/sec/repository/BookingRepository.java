package com.sec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Booking;
import com.sec.entity.User;

public interface BookingRepository extends CrudRepository<Booking, Long> {
	public Booking save(Booking booking);
	public List<Booking> findAllByOwner(User user);
}

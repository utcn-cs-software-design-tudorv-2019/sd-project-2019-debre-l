package com.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Booking;
import com.sec.entity.User;
import com.sec.repository.BookingRepository;

@Service
public class BookingService {
private BookingRepository bookingRepository;
	
	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository=bookingRepository;
	}
	
	public Booking newBooking(Booking booking) throws Exception
	{
		if(booking.getBookedOffer().getStock()>=booking.getQuantity())
		{
			return bookingRepository.save(booking);
		}
		else
		{
			throw new Exception("Stock insufficient");
		}
	}
	
	public List<Booking> getAllBookingByUser(User user)
	{
		return bookingRepository.findAllByOwner(user);
	}
}

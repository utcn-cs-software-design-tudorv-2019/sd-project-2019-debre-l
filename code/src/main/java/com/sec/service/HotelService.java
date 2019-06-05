package com.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Hotel;
import com.sec.entity.Location;
import com.sec.repository.HotelRepository;

@Service
public class HotelService {
private HotelRepository hotelRepository;
	
	@Autowired
	public HotelService(HotelRepository hotelRepostiory) {
		this.hotelRepository=hotelRepostiory;
	}
	
	public Hotel newHotel(Hotel hotel)
	{
		return hotelRepository.save(hotel);
	}
	
	public Hotel getById(Long ID)
	{
		return hotelRepository.findById(ID).get();
	}
	
	public Iterable<Hotel> getAllHotels()
	{
		return hotelRepository.findAll();
	}
	
	public Iterable<Hotel> getAllHotelsByLocation(Location location)
	{
		return hotelRepository.findByLocation(location);
	}
	
	public Hotel getByName(String name)
	{
		return hotelRepository.findByName(name);
	}
}

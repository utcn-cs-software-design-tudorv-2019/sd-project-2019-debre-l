package com.sec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Hotel;
import com.sec.entity.Location;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
	public Iterable<Hotel> findByLocation (Location location);
	public Hotel findByName(String name);
}

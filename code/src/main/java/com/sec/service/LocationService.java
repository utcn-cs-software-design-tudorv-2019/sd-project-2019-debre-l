package com.sec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Location;
import com.sec.repository.LocationRepository;

@Service
public class LocationService {
	
	private LocationRepository locationRepository;
	
	@Autowired
	public LocationService(LocationRepository locationRepostiory) {
		this.locationRepository=locationRepostiory;
	}
	
	public Location newLocation(Location location)
	{
		return locationRepository.save(location);
	}
	
	public Location getLocationByID(Long ID)
	{
		return locationRepository.findById(ID).get();
	}
	
}

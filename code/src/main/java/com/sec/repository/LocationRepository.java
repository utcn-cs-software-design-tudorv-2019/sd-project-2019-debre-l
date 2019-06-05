package com.sec.repository;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
	public Location findByCity(String city);
}

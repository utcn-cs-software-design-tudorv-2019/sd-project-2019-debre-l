package com.sec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Hotel;
import com.sec.entity.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
	public List<Offer> findByHotel (Hotel hotel);
	
}

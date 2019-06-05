package com.sec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.entity.Hotel;
import com.sec.entity.Offer;
import com.sec.repository.OfferRepository;

@Service
public class OfferService {
	private OfferRepository offerRepository;
	
	@Autowired
	public OfferService(OfferRepository offerRepository) {
		this.offerRepository=offerRepository;
	}
	
	public Offer newOffer(Offer offer)
	{
		return offerRepository.save(offer);
	}
	
	public Offer getLocationByID(Long ID)
	{
		return offerRepository.findById(ID).get();
	}
	
	public List<Offer> getAllOffersByHotels(Iterable<Hotel> hotel)
	{
		List<Offer> oferta = new ArrayList<Offer>();
		for(Hotel h : hotel)
		{
			List<Offer> temp = offerRepository.findByHotel(h);
			for(Offer o : temp)
			{
				oferta.add(o);
				System.out.println(o);
			}
		}
		return oferta;
	}
	
	public Offer getOfferById(Long id) throws Exception
	{
		Optional<Offer> temp = offerRepository.findById(id);
		if(temp.isPresent())
		{
			return temp.get();
		}
		else
		{
			throw new Exception("getOfferById error id not found");
		}
	}
}

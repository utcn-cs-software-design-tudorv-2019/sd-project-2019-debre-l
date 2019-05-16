package com.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;

@Entity
@Table (name = "location")
public class Location {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlocation", unique = true, nullable = false)	
	private int idlocation;
	@Column(name="country",nullable=false)
	private String country;
	@Column(name="address",nullable=false)
	private String address;
	@OneToMany(mappedBy="location",fetch=FetchType.EAGER)
	private List<Hotel> hotels;
	
	public Location()
	{
		super();
	}
	
	public Location(int idlocation, String country, String address, List<Hotel> hotels) {
		super();
		this.idlocation = idlocation;
		this.country = country;
		this.address = address;
		this.hotels = hotels;
	}

	public int getIdlocation() {
		return idlocation;
	}

	public void setIdlocation(int idlocation) {
		this.idlocation = idlocation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	
	
}

package com.sec.entity;

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
@Table (name = "locations")
public class Location {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlocation", unique = true, nullable = false)	
	private Long idlocation;
	
	@Column(name="country",nullable=false)
	private String country;
	
	@Column(name="city",nullable=false)
	private String city;
	
	@OneToMany(mappedBy="location",fetch=FetchType.EAGER)
	private List<Hotel> hotels;
	
	public Location()
	{
		super();
	}
	
	public Location(Long idlocation, String country, String city, List<Hotel> hotels) {
		super();
		this.idlocation = idlocation;
		this.country = country;
		this.city = city;
		this.hotels = hotels;
	}

	public Long getIdlocation() {
		return idlocation;
	}

	public void setIdlocation(Long idlocation) {
		this.idlocation = idlocation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	@Override
	public String toString() {
		return "Location [idlocation=" + idlocation + ", country=" + country + ", city=" + city + ", hotels=" + hotels
				+ "]";
	}
	
	
}

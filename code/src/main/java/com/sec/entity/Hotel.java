package com.sec.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "hotels")
public class Hotel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhotel", unique = true, nullable = false)	
	private Long idhotel;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@ManyToOne()
	@JoinColumn(name="locationID")
	private Location location;
	
	@Column(name="address",nullable=false)
	private String address;
	
	@OneToMany(mappedBy="hotel",fetch=FetchType.EAGER)
	private List<Package> packages;
	
	public Hotel()
	{
		super();
	}
	
	public Hotel(Long idhotel, String name, Location location, String address) {
		super();
		this.idhotel = idhotel;
		this.name = name;
		this.location = location;
		this.address = address;
	}

	public Long getIdhotel() {
		return idhotel;
	}

	public void setIdhotel(Long idhotel) {
		this.idhotel = idhotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Hotel [idhotel=" + idhotel + ", name=" + name + ", location=" + location + ", address=" + address
				+ ", packages=" + packages + "]";
	}
	
	
	
}

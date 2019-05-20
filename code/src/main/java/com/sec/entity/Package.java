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
@Table (name = "packages")
public class Package {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpackage", unique = true, nullable = false)	
	private int idpackage;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@ManyToOne()
	@JoinColumn(name="packages")
	private Hotel hotel;
	
	@Column(name="transport",nullable=false)
	private String transport;
	
	@Column(name="price",nullable=false)
	private float price;
	
	@Column(name="stock",nullable=false)
	private int stock;
	
	@OneToMany(mappedBy="bookedPackage",fetch=FetchType.EAGER)
	private List<Booking> reservations;
	
	public Package() {
		super();
	}

	public Package(int idpackage, String name, Hotel hotel, String transport, float price, int stock,
			List<Booking> reservations) {
		super();
		this.idpackage = idpackage;
		this.name = name;
		this.hotel = hotel;
		this.transport = transport;
		this.price = price;
		this.stock = stock;
		this.reservations = reservations;
	}

	public int getIdpackage() {
		return idpackage;
	}

	public void setIdpackage(int idpackage) {
		this.idpackage = idpackage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Booking> getReservations() {
		return reservations;
	}

	public void setReservations(List<Booking> reservations) {
		this.reservations = reservations;
	}
	
	
	
	
}

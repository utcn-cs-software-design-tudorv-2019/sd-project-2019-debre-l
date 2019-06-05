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
@Table (name = "offers")
public class Offer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoffer", unique = true, nullable = false)	
	private Long idoffer;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@ManyToOne()
	@JoinColumn(name="hotelid")
	private Hotel hotel;
	
	@Column(name="transport",nullable=false)
	private String transport;
	
	@Column(name="price",nullable=false)
	private float price;
	
	@Column(name="stock",nullable=false)
	private int stock;
	
	@Column(name="period",nullable=false)
	private int period;
	
	@OneToMany(mappedBy="bookedOffer",fetch=FetchType.EAGER)
	private List<Booking> reservations;
	
	public Offer() {
		super();
	}

	public Offer(Long idoffer, String name, Hotel hotel, String transport, float price, int stock,
			List<Booking> reservations, int period) {
		super();
		this.idoffer = idoffer;
		this.name = name;
		this.hotel = hotel;
		this.transport = transport;
		this.price = price;
		this.stock = stock;
		this.period=period;
		this.reservations = reservations;
	}

	public Long getIdoffer() {
		return idoffer;
	}

	public void setIdoffer(Long idoffer) {
		this.idoffer = idoffer;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "IDoffer=" + idoffer + ", name=" + name + ", transport=" + transport
				+ ", price=" + price + ", stock=" + stock + ", period=" + period + "\n";
	}	
	
	
}

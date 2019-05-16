package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "package")
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
	public Package() {
		super();
	}
	public Package(int idpackage, String name, Hotel hotel, String transport, float price) {
		super();
		this.idpackage = idpackage;
		this.name = name;
		this.hotel = hotel;
		this.transport = transport;
		this.price = price;
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
	
	
}

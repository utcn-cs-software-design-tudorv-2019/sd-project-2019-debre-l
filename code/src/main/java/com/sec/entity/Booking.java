package com.sec.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "bookings")
public class Booking {
	@Id
    @Column(name = "idbooking", unique = true, nullable = false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbooking;
	
	@ManyToOne()
	@JoinColumn(name="owner")
	private User owner;
	
	@ManyToOne()
	@JoinColumn(name="offerid")
	private Offer bookedOffer;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="reservationDate",nullable=false)
	private Date reservationDate;
	
	public Booking()
	{
		super();
	}

	public Booking(Long idbooking, User owner, Offer bookedOffer, int quantity, Date reservationDate) {
		super();
		this.idbooking = idbooking;
		this.owner = owner;
		this.bookedOffer = bookedOffer;
		this.reservationDate = reservationDate;
		this.quantity=quantity;
	}
	
	public Booking(User owner, Offer bookedOffer, int quantity, Date reservationDate) {
		super();
		this.owner = owner;
		this.bookedOffer = bookedOffer;
		this.reservationDate = reservationDate;
		this.quantity=quantity;
	}

	public Long getIdbooking() {
		return idbooking;
	}

	public void setIdbooking(Long idbooking) {
		this.idbooking = idbooking;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Offer getBookedOffer() {
		return bookedOffer;
	}

	public void setBookedOffer(Offer bookedOffer) {
		this.bookedOffer = bookedOffer;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Offer: "+bookedOffer.getName()+", quantity:"+quantity+", price:"+bookedOffer.getPrice();
	}

	
	
}

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
	@JoinColumn(name="packageid")
	private Package bookedPackage;
	
	@Column(name="reservationDate",nullable=false)
	private Date reservationDate;
	
	@Column(name="payed",nullable=false)
	private boolean payed;
	
	public Booking()
	{
		super();
	}

	public Booking(Long idbooking, User owner, Package bookedPackage, Date reservationDate, boolean payed) {
		super();
		this.idbooking = idbooking;
		this.owner = owner;
		this.bookedPackage = bookedPackage;
		this.reservationDate = reservationDate;
		this.payed = payed;
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

	public Package getBookedPackage() {
		return bookedPackage;
	}

	public void setBookedPackage(Package bookedPackage) {
		this.bookedPackage = bookedPackage;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	
	
}

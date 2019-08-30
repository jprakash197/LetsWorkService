package com.mindtree.letswork.module.venue.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "BOOKINGS")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;

	@Column
	private Date date;
	
	@Column
	private int durationInHours;
	
	@Column
	private Time time;
	
	@ManyToOne
	private Venue venue;
	
	public Booking() {
		super();
	}

	public Booking(int bookingId, Date date, int durationInHours, Time time) {
		super();
		this.bookingId = bookingId;
		this.date = date;
		this.durationInHours = durationInHours;
		this.time = time;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
}

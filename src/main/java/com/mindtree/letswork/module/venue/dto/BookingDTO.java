package com.mindtree.letswork.module.venue.dto;

import java.sql.Date;
import java.sql.Time;

public class BookingDTO {

	private int bookingId;

	private Date date;

	private int durationInHours;
	
	private Time startTime;
	
	public BookingDTO() {
		
	}

	public BookingDTO(int bookingId, Date date, int durationInHours, Time startTime) {
		super();
		this.bookingId = bookingId;
		this.date = date;
		this.durationInHours = durationInHours;
		this.startTime = startTime;
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
}

package com.mindtree.letswork.module.venue.dto;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class VenueRequestDTO {

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date date;

	@NotNull
	private int capacity;

	@NotNull
	private String venueType;

	@NotNull
	private String city;

	public VenueRequestDTO() {
		super();
	}

	public VenueRequestDTO(Date date, int capacity, String venueType, String city) {
		super();
		this.date = date;
		this.capacity = capacity;
		this.venueType = venueType;
		this.city = city;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getVenueType() {
		return venueType;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
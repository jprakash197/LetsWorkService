package com.mindtree.letswork.module.venue.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VENUES")
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venue_id")
	private int venueId;

	@Column
	private String venueName;
	
	@Column
	private String city;
	
	@Column
	private String address;
	
	@Column
	private double size;
	
	@Column
	private int capacity;
	
	@Column
	private String description;
	
	@Column
	private int rating;
	
	@Column
	private double price;
	
	@Column
	private String venueType;

	@OneToMany(mappedBy="venue")
	private Set<Booking> bookings;
	
	public Venue() {
		super();
	}

	public Venue(int venueId, String venueName, String city, String address, double size, int capacity,
			String description, int rating, double price, String venueType) {
		super();
		this.venueId = venueId;
		this.venueName = venueName;
		this.city = city;
		this.address = address;
		this.size = size;
		this.capacity = capacity;
		this.description = description;
		this.rating = rating;
		this.price = price;
		this.venueType = venueType;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getVenueType() {
		return venueType;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
}

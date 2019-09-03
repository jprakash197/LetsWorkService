package com.mindtree.letswork.module.venue.service.serviceimp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.venue.entity.Booking;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.ResourceNotFoundException;
import com.mindtree.letswork.module.venue.repo.VenueRepo;
import com.mindtree.letswork.module.venue.service.VenueService;

@Service
public class VenueServiceImp implements VenueService {

	@Autowired
	VenueRepo venueRepo;

	@Override
	public List<Venue> getFinalSearchedVenues(String type, Date date, int capacity, String city)
			throws ResourceNotFoundException {
		List<Venue> venues = new ArrayList<Venue>();
		venues = venueRepo.findAll();
		if (type != "")
			venues = venues.stream().filter(venue -> type.equalsIgnoreCase(venue.getVenueType()))
					.collect(Collectors.toList());
		if (chechkDate(date))
			venues = venues.stream().filter(venue -> checkAvailability(venue, date)).collect(Collectors.toList());
		else
			throw new ResourceNotFoundException("Invalid date");

		if (!getCities().contains(city))
			throw new ResourceNotFoundException("No venues for this city");
		else
			venues = venues.stream().filter(venue -> city.equalsIgnoreCase(venue.getCity()))
					.collect(Collectors.toList());

		if (capacity != 0)
			venues = venues.stream().filter(venue -> venue.getCapacity() >= capacity).collect(Collectors.toList());

		if (venues.size() != 0)
			return venues;
		else
			throw new ResourceNotFoundException("No venues available");
	}

	private boolean chechkDate(Date date) throws ResourceNotFoundException {
		java.util.Date utilDate = new java.util.Date(date.getTime());
		java.util.Date currentDate = new java.util.Date();
		if (currentDate.compareTo(utilDate) > 0)
			throw new ResourceNotFoundException("Cannot book for this date");
		return true;
	}

	@Override
	public boolean checkAvailability(Venue venue, java.sql.Date date) {
		boolean booked = true;
		for (Booking b : venue.getBookings()) {
			if (date.getMonth() == b.getDate().getMonth() && date.getDay() == b.getDate().getDay()
					&& date.getYear() == b.getDate().getYear()) {
				booked = false;
			}
		}
		return booked;
	}

	@Override
	public Set<String> getCities() {
		Set<String> cities = new HashSet<String>();
		venueRepo.findAll().forEach(venue -> cities.add(venue.getCity()));
		return cities;
	}
}

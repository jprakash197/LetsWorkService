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
import com.mindtree.letswork.module.venue.repo.VenueRepo;
import com.mindtree.letswork.module.venue.service.VenueService;

@Service
public class VenueServiceImp implements VenueService{

	@Autowired
	VenueRepo venueRepo;
	
	@Override
	public List<Venue> getFinalSearchedVenues(String type, Date date, int capacity, String city) {
		List<Venue> venues=new ArrayList<Venue>();
		
		venues=venueRepo.findAll();
		
		if(type!="")
		venues=venues.stream().filter(venue -> type.equalsIgnoreCase(venue.getVenueType())).collect(Collectors.toList());
		
		venues=venues.stream().filter(venue -> checkAvailability(venue,date)).collect(Collectors.toList());
		
		if(city!="")
		venues=venues.stream().filter(venue -> city.equalsIgnoreCase(venue.getCity())).collect(Collectors.toList());
		
		if(capacity!=0)
		venues=venues.stream().filter(venue -> venue.getCapacity()>=capacity).collect(Collectors.toList());
		
		return venues;
	}
	
	@Override
	public boolean checkAvailability(Venue venue, java.sql.Date date) {
		boolean booked = true;
		for (Booking b : venue.getBookings()) {
			if (date.getMonth()==b.getDate().getMonth()&&date.getDay()==b.getDate().getDay()&&date.getYear()==b.getDate().getYear()){
				booked = false;
			}
		}
		return booked;
	}
	
	@Override
	public Set<String> getCities() {
		Set<String> cities=new HashSet<String>();
		venueRepo.findAll().forEach(venue -> cities.add(venue.getCity()));
		return cities;
	}
}

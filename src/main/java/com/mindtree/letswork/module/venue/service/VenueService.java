package com.mindtree.letswork.module.venue.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.mindtree.letswork.module.venue.entity.Venue;

public interface VenueService {
	
	/**
	 * @param venue
	 * @param date
	 * @return true if venue is available for given date
	 */
	public boolean checkAvailability(Venue venue,Date date);

	/**
	 * @param type
	 * @param date
	 * @param capacity
	 * @param city
	 * @return list of venues after searching
	 */
	public List<Venue> getFinalSearchedVenues(String type,Date date,int capacity,String city);
	
	public Set<String> getCities();
}

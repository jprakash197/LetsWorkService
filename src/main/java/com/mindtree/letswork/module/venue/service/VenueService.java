package com.mindtree.letswork.module.venue.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.ResourceNotFoundException;

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
	 * @throws ResourceNotFoundException 
	 */
	public List<Venue> getFinalSearchedVenues(String type,Date date,int capacity,String city) throws ResourceNotFoundException;
	
	public Set<String> getCities();
}

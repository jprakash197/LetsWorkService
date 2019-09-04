package com.mindtree.letswork.module.venue.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.dto.VenueRequestDTO;
import com.mindtree.letswork.module.venue.exception.CityNotFoundException;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@CrossOrigin
@RestController
public class VenueController {

	@Autowired
	private VenueServiceImpl venueService;

	@Autowired
	private DTOUtil dtoUtil;

	@PostMapping("/venues")
	public ResponseEntity<?> getVenues(@Valid @RequestBody VenueRequestDTO details) throws VenueException 
			 {
		List<VenueDTO> venuesDto = new ArrayList<VenueDTO>();
		venueService
				.getFinalSearchedVenues(details.getVenueType(), details.getDate(), details.getCapacity(),
						details.getCity())
				.forEach(venue -> venuesDto.add((VenueDTO) dtoUtil.convert(venue, VenueDTO.class)));

		if(venuesDto.size()!=0)
			return ResponseEntity.ok().body(venuesDto);
		
		else
			return ResponseEntity.ok().body("No venues available currently");

	}

	@GetMapping("/cities")
	public ResponseEntity<Set<String>> getCities() {
		return ResponseEntity.ok().body(venueService.getCities());
	}
}

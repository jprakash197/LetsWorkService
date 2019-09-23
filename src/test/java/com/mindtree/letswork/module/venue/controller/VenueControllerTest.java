package com.mindtree.letswork.module.venue.controller;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@RunWith(SpringRunner.class)
public class VenueControllerTest {

	private MockMvc mockMvc;

	@Mock
	private VenueServiceImpl venueService;
	
	@Mock
	private DTOUtil dtoUtil;

	@InjectMocks
	private VenueController venueController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(venueController).build();
	}

	@Test
	public void getVenuesTest() throws Exception {

		String request = "/venues/100/Bangalore/Meeting/2020-09-20";
		Set<Booking> bookings = new HashSet<>();
		Set<Image> images = new HashSet<>();
		Set<VenueFeatures> features = new HashSet<>();
		Venue venue1 = new Venue("MR-01", "Bangalore", "J.P.Nagar", 1000, 100, "Meeting room with sufficient space", 4,
				5000, "Meeting", bookings, images, features);
		Venue venue2 = new Venue("CR-01", "Bangalore", "Whitefield", 2000, 100, "Conference room with sufficient space",
				3, 6000, "Conference", bookings, images, features);
		List<Venue> venues = new ArrayList<>();
		venues.add(venue1);
		venues.add(venue2);

		java.sql.Date date = Date.valueOf("2020-09-20");
		Mockito.when(venueService.getFinalSearchedVenues("Meeting", date, 100, "Bangalore")).thenReturn(venues);
		mockMvc.perform(MockMvcRequestBuilders.get(request).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void getAllVenueTest() throws Exception {
		List<Venue> venues = new ArrayList<>();
		VenueDTO venue1 = new VenueDTO(ThreadLocalRandom.current().nextInt(0, 100), "Cow", "Mumbai",
				"666 W Fleet Street", ThreadLocalRandom.current().nextDouble(0, 50),
				ThreadLocalRandom.current().nextInt(0, 100), "COWOWOWOWOWOWOWOWOWOWOWOWOW",
				ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(0, 10000),
				"Conference", null, null);

		venues.add((Venue) dtoUtil.convert(venue1, Venue.class));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/venues"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

		Mockito.when(venueService.getAllVenues()).thenReturn(venues);

		assertEquals(venues.size(), 1);

		ResponseEntity<?> venuesReturned = venueController.getAllVenues();
		List<VenueDTO> venuesDto = new ArrayList<>();
		venues.forEach(venue -> venuesDto.add((VenueDTO) dtoUtil.convert(venue, VenueDTO.class)));
		assertEquals(venuesDto, venuesReturned.getBody());
	}

	@Test
	public void getCitiesTest() throws Exception {
		Set<String> citiesResult = new HashSet<String>();
		citiesResult.add("Bangalore");
		citiesResult.add("Mumbai");
		citiesResult.add("Bhubaneswar");
		Mockito.when(venueService.getCities()).thenReturn(citiesResult);

		mockMvc.perform(MockMvcRequestBuilders.get("/cities")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void postAVenueTest() throws VenueException {
		VenueDTO venue = new VenueDTO();

		venue.setVenueId(ThreadLocalRandom.current().nextInt(0, 100));

		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/venuesz", venue))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		} catch (Exception e) {
//			assertEquals(expected, actual);
		}

//		ResponseEntity<?> venueSaved = venueController.postVenue(venue);
//
//		assertEquals(venue, venueSaved.getBody());
	}

	@Test
	public void deleteAVenue() throws VenueException {	
		VenueDTO venue = new VenueDTO();

		venue.setVenueId(ThreadLocalRandom.current().nextInt(0, 100));
		
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.delete("/venues", venue))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		} catch(Exception e) {
			
		}

//		ResponseEntity<?> venueSaved = venueController.postVenue(venue);
//		ResponseEntity<?> venueDeleted = venueController.deleteVenue(venueId);
//		assertEquals(venueDeleted, venueSaved);
	}

	
}

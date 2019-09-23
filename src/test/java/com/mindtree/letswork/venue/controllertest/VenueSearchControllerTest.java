package com.mindtree.letswork.venue.controllertest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.controller.VenueController;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueSearchControllerTest {

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
	public void getCitiesTest() throws Exception {
		Set<String> citiesResult = new HashSet<String>();
		citiesResult.add("Bangalore");
		citiesResult.add("Mumbai");
		citiesResult.add("Bhubaneswar");
		Mockito.when(venueService.getCities()).thenReturn(citiesResult);

		mockMvc.perform(MockMvcRequestBuilders.get("/cities")).andExpect(MockMvcResultMatchers.status().isOk());
	}
}

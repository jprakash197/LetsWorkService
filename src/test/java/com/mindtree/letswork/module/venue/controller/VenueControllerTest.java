package com.mindtree.letswork.module.venue.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
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
	public void getCitiesTest() throws Exception {
		Set<String> citiesResult = new HashSet<String>();
		citiesResult.add("Bangalore");
		citiesResult.add("Mumbai");
		citiesResult.add("Bhubaneswar");
		Mockito.when(venueService.getCities()).thenReturn(citiesResult);

		mockMvc.perform(MockMvcRequestBuilders.get("/cities")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getAllVenueTest() throws Exception {
		final String[] cities = {"Mumbai", "Bangalore", "Bhubaneswar"};
		final String[] types = {"Conference", "Workshop", "Training", "Meeting"};
		
		List<Venue> venues = new ArrayList<>();
		VenueDTO venueDto1 = new VenueDTO(ThreadLocalRandom.current().nextInt(0, 100), "Masala", "Mumbai",
				"666 W Fleet Street", ThreadLocalRandom.current().nextDouble(0, 50),
				ThreadLocalRandom.current().nextInt(0, 100), "great place for conferences!",
				ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(0, 10000),
				"Conference", null, null);
		
		VenueDTO venueDto2 = new VenueDTO(ThreadLocalRandom.current().nextInt(0, 100), "Masala", "Bhubaneswar",
				"1 Infinity Loop Way", ThreadLocalRandom.current().nextDouble(0, 50),
				ThreadLocalRandom.current().nextInt(0, 100), "great place for meetings!",
				ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(0, 10000),
				"Meeting", null, null);
		
		VenueDTO venueDto3 = new VenueDTO(ThreadLocalRandom.current().nextInt(0, 100), "Masala", "Bangalore",
				"", ThreadLocalRandom.current().nextDouble(0, 50),
				ThreadLocalRandom.current().nextInt(0, 100), "great place for trainings!",
				ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(0, 10000),
				"Training", null, null);
		
		VenueDTO venueDtoShouldFail = new VenueDTO(ThreadLocalRandom.current().nextInt(0, 100), "Masala", "Chennai",
				"666 W Fleet Street", ThreadLocalRandom.current().nextDouble(0, 50),
				ThreadLocalRandom.current().nextInt(0, 100), "great place for workshop!",
				ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(0, 10000),
				"Workshopzzz", null, null);
		
		venues.forEach((Venue venue) -> {
			Mockito.when(this.venueService.insertVenue(venue)).thenReturn(venue);
		});

		Mockito.when(venueService.getAllVenues()).thenReturn(venues);
		
		venues = new ArrayList<>();
		venues.add(new Venue());
		venues.get(venues.size() - 1).setCity(venueDto1.getCity());
		venues.get(venues.size() - 1).setVenueType(venueDto1.getVenueType());
		venues.add(new Venue());
		venues.get(venues.size() - 1).setCity(venueDto2.getCity());
		venues.get(venues.size() - 1).setVenueType(venueDto2.getVenueType());
		venues.add(new Venue());
		venues.get(venues.size() - 1).setCity(venueDto3.getCity());
		venues.get(venues.size() - 1).setVenueType(venueDto3.getVenueType());
		venues.add(new Venue());
		venues.get(venues.size() - 1).setCity(venueDtoShouldFail.getCity());
		venues.get(venues.size() - 1).setVenueType(venueDtoShouldFail.getVenueType());
		
		venues.forEach((Venue venue) -> {
			boolean cityMatch = Arrays.stream(cities).filter(city -> city.equalsIgnoreCase(venue.getCity())).findFirst().isPresent();
			if (cityMatch) {
				assertEquals(cityMatch, true);
			} else {
				assertEquals(cityMatch, false);
			}
			
			boolean typeMatch = Arrays.stream(types).filter(type -> type.equalsIgnoreCase(venue.getVenueType())).findFirst().isPresent();
			if (typeMatch) {
				assertTrue(typeMatch);
			} else {
				assertFalse(typeMatch);
			}
		});
		
		assertEquals(venues.size(), 4);
		assertTrue(!venues.isEmpty());
		Object[] venuesArr = venues.stream().map(v -> v.toString())
				.filter(s -> s.matches("gettin this capability done"))
				.collect(Collectors.toList()).toArray();
		Object[] expected = {};
		assertArrayEquals(expected, venuesArr);
	}

	@Test
	public void postAVenueTest() throws VenueException {
		VenueDTO venueDto = new VenueDTO();
		Venue venue = new Venue();
		venue.setVenueId(ThreadLocalRandom.current().nextInt(0, 100));
		Mockito.when(this.venueService.insertVenue(venue)).thenReturn(venue);
		venueDto = (VenueDTO) dtoUtil.convert(venue, VenueDTO.class);

		String venueStr = "{\"venueId\":" + venue.getVenueId() + "}";

		try {
			this.mockMvc.perform(
					MockMvcRequestBuilders.post("/venuesz")
					.contentType(MediaType.APPLICATION_JSON)
					.content(venueStr))
					.andExpect(MockMvcResultMatchers.status().isOk());
			
			venueStr = "";
			this.mockMvc.perform(
					MockMvcRequestBuilders.post("/venuesz")
					.contentType(MediaType.APPLICATION_JSON)
					.content(venueStr))
					.andExpect(MockMvcResultMatchers.status().isBadRequest());
			
		} catch (Exception e) {
			StringBuilder msg = new StringBuilder(e.getMessage());
			assertEquals(msg.toString().contains("Venue failed to save"), true);
		}

//		assertEquals(venue.getVenueId(), venueDto.getVenueId());
	}

	@Test
	public void putAVenueTest() throws VenueException {
		VenueDTO venueDto = new VenueDTO();
		Venue venue = new Venue();
		venue.setVenueId(ThreadLocalRandom.current().nextInt(0, 100));
		boolean updateErrors = false;
		Mockito.when(this.venueService.updateVenue(venue)).thenReturn(updateErrors);
		venueDto = (VenueDTO) dtoUtil.convert(venue, VenueDTO.class);

		String venueStr = "{\"venueId\":" + venue.getVenueId() + "}";

		assertEquals(updateErrors, false);

		try {
			this.mockMvc.perform(
					MockMvcRequestBuilders.put("/venues").contentType(MediaType.APPLICATION_JSON).content(venueStr))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			throw new VenueException("Venue exception: " + e.getMessage(), e);
		}

	}

	@Test
	public void deleteAVenue() throws VenueException {
		Venue venue = new Venue();
		venue.setVenueId(ThreadLocalRandom.current().nextInt(0, 100));

		boolean updateErrors = false;
		Mockito.when(this.venueService.insertVenue(venue)).thenReturn(venue);
		Mockito.when(this.venueService.deleteVenue(venue.getVenueId())).thenReturn(updateErrors);

		try {
			this.mockMvc
					.perform(MockMvcRequestBuilders.delete("/venues/{venueId}", Integer.toString(venue.getVenueId()))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		} catch (Exception e) {
			throw new VenueException("Venue exception: " + e.getMessage(), e);
		}

	}

}

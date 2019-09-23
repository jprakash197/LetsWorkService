package com.mindtree.letswork.venue.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.controller.VenueController;
import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueControllerTest {

	@Autowired
	private VenueController venueController;

	@MockBean
	private VenueServiceImpl venueService;

	@MockBean
	private DTOUtil dtoutil;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(venueController).build();
	}
	
	@Test
	public void getDetailsTest() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue room = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		VenueDTO venueDto = (VenueDTO) dtoutil.convert(room, VenueDTO.class);
		Mockito.when(venueService.getVenueDetails(1)).thenReturn(room);
		ResponseEntity<?> actual = venueController.getDetails(1);
		assertEquals(venueDto, actual.getBody());

	}

	@Test
	public void getAllVenueTest() throws Exception {
		List<Venue> venues = new ArrayList<>();
		VenueDTO venue1 = new VenueDTO(
				ThreadLocalRandom.current().nextInt(0, 100),
				"Cow", "Mumbai", "666 W Fleet Street",
				ThreadLocalRandom.current().nextDouble(0, 50),
				ThreadLocalRandom.current().nextInt(0, 100),
				"COWOWOWOWOWOWOWOWOWOWOWOWOW",
				ThreadLocalRandom.current().nextInt(0, 10),
				ThreadLocalRandom.current().nextDouble(0, 10000),
				"Conference", null, null);
		
		venues.add((Venue) dtoutil.convert(venue1, Venue.class));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/venues"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
//		List<Venue> venuesReturned = venueService.getAllVenues();
		Mockito.when(venueService.getAllVenues()).thenReturn(venues);

		assertEquals(venues.size(), 1);
		
		ResponseEntity<?> venuesReturned = venueController.getAllVenues();
	}

//	@Test
//	public void postAVenue() throws VenueException {
//		VenueDTO venue = new VenueDTO();
//
//		ResponseEntity<?> venueSaved = venueController.postVenue(venue);
//
//		assertEquals(venue, venueSaved.getBody());
//	}
//
//	@Test
//	public void deleteAVenue() throws VenueException {
//		VenueDTO venue = new VenueDTO();
//		final int venueId = this.venueService.getAllVenues().size() + 1;
//		venue.setVenueId(venueId);
//
//		ResponseEntity<?> venueSaved = venueController.postVenue(venue);
//		ResponseEntity<?> venueDeleted = venueController.deleteVenue(venueId);
//		assertEquals(venueDeleted, venueSaved);
//	}
	
}

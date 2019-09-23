package com.mindtree.letswork.venue.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.controller.VenueController;
import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.repository.VenueRepo;
import com.mindtree.letswork.module.venue.service.VenueService;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VenueServiceTest {
	@TestConfiguration
	static class VenueServiceContextConfig {
		@Bean
		public VenueService roomService() {
			return new VenueServiceImpl();
		}
	}

	@Autowired
	private VenueController venueController;
	
	@Autowired
	private VenueService venueService;

	@MockBean
	private VenueRepo venueRepo;

	@Test(expected=VenueException.class)
	public void getVenueByIdTest() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue room = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		 
		Mockito.when(venueRepo.findById(1)).thenReturn(Optional.of(room));
		Venue venueFound = venueService.getVenueDetails(1);

		assertEquals(venueFound.getVenueName(), room.getVenueName());
		assertNotEquals(venueFound.getVenueName(), "eirwe");
		assertEquals(VenueException.class,venueService.getVenueDetails(41));
	}
	
	
	@Test
	public void getVenueByNameTest() throws VenueException {
		final String cannotBook = "Compton";
		
		Set<String> cities = venueService.getCities();
		String solid = cities.stream().filter(s -> s.equals(cannotBook)).findFirst().orElse("Solid");
		
		assertEquals(solid, "Solid");
		assertEquals(cities.size(), 0);
	}
	
	@Test
	public void checkVenueDateDoesNotEqualPrice() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue venue = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		
		Mockito.when(this.venueRepo.findById(1)).thenReturn(Optional.of(venue));
		Venue venueFound = this.venueService.getVenueDetails(1);
		
		Date date = new Date(12, 25, 2019);
		
		assertNotEquals(venueFound.getPrice(), date.getDay());
	}
	
	@Test
	public void checkVenuePriceIsNotAddress() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue venue = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
		
		Mockito.when(this.venueRepo.findById(1)).thenReturn(Optional.of(venue));
		Venue venueFound = this.venueService.getVenueDetails(1);
		
		assertNotEquals(venueFound.getPrice(), venueFound.getAddress());
	}

	
	@Test
	public void getAllVenueTest() throws VenueException {
		final int TOTAL_VENUES = 21;
		List<VenueDTO> venues = new ArrayList<>();
		Mockito.when(venueRepo.findAll().size()).thenReturn(21);

		venues = venueController.getAllVenues();

		assertNotEquals(venues.size(), 0);
	}
	
	@Test
	public void postAVenue() throws VenueException {
		VenueDTO venue = new VenueDTO();
		
		ResponseEntity<?> venueSaved = venueController.postVenue(venue);

		assertEquals(venue, venueSaved.getBody());
	}
}

package com.mindtree.letswork.venue.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.repository.VenueRepo;
import com.mindtree.letswork.module.venue.service.VenueService;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;

@RunWith(SpringRunner.class)
public class VenueServiceTest {
	@TestConfiguration
	static class VenueServiceContextConfig {
		@Bean
		public VenueService roomService() {
			return new VenueServiceImpl();
		}
	}

	@Autowired
	private VenueService venueService;

	@MockBean
	private VenueRepo venueRepo;

	@Test
	public void getVenueByIdTest() throws VenueException {
		Set<Image> img = new HashSet<>();
		Set<VenueFeatures> feature = new HashSet<>();
		Set<Booking> bookings = new HashSet<>();
		Venue room = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);

		Mockito.when(venueRepo.findById(1)).thenReturn(Optional.of(room));
		Venue venueFound = venueService.getVenueDetails(1);

		assertEquals(venueFound.getVenueName(), room.getVenueName());
		assertNotEquals(venueFound.getVenueName(), "eirwe");
	}
}

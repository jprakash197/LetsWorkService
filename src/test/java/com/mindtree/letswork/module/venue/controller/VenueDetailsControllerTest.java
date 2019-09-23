package com.mindtree.letswork.module.venue.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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
public class VenueDetailsControllerTest {

	@Autowired
	private VenueController venueController;

	@MockBean
	private VenueServiceImpl venueService;

	@MockBean
	private DTOUtil dtoutil;

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

//	@Test
//	public void getAllVenueTest() throws VenueException {
//		final int TOTAL_VENUES = 22;
//		List<VenueDTO> venues = new ArrayList<>();
//		Mockito.when(venueService.getAllVenues().size()).thenReturn(TOTAL_VENUES);
//		final int venuesReturned = venueService.getAllVenues().size();
//		
//		venues = venueController.getAllVenues();
//
//		assertNotEquals(venues.size(), 0);
//	}

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

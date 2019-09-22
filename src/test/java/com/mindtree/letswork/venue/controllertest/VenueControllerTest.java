package com.mindtree.letswork.venue.controllertest;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.constant.VenueFeatures;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.venue.controller.VenueController;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
import com.mindtree.letswork.module.venue.exception.VenueException;
import com.mindtree.letswork.module.venue.service.impl.VenueServiceImpl;
import com.mindtree.letswork.module.venue.util.DTOUtil;

@RunWith(SpringRunner.class)
public class VenueControllerTest {
//	private MockMvc mockMvc;
//
//	@Mock
//	private VenueService roomServ;
//
//	@InjectMocks
//	private VenueController venueController;
//
//	@Before
//	public void init() {
//		// creates mockmvc object,creates mock of venueController
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(venueController).build();
//	}
//
//	@Test
//	public void getDetailsTest() throws Exception {
//		Set<Image> img = new HashSet<>();
//		Set<VenueFeatures> feature = new HashSet<>();
//		Set<Booking> bookings = new HashSet<>();
//		Venue room = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training", bookings, img, feature);
//
////		VenueDTO venueDto = (VenueDTO) dtoUtil.convert(room, VenueDTO.class);
//
//		Mockito.when(roomServ.getVenueDetails(1)).thenReturn(room);
//	
//		mockMvc.perform(MockMvcRequestBuilders.get("/getDetails/{id}", 1).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//		Mockito.verify(roomServ).getVenueDetails(1);
//		
//	
//
//	}
	@TestConfiguration
	static class VenueControllerContextConfig {
		@Bean
		public VenueController venueController() {
			return new VenueController();
		}
	}

	@Autowired
	private VenueController venueController;

	@MockBean
	private VenueServiceImpl venueService;

	@MockBean
	private DTOUtil dtoutil;

	@Test
	public void getDetailsTest() throws VenueException {
//		Set<Image> img = new HashSet<>(); 
//		Set<VenueFeatures> feature=new HashSet<>(); 
//		Set<Booking> bookings=new HashSet<>();
//		Venue room = new Venue("abc", "xyz", "asd", 123, 300, "qwertyu", 4, 1200, "training");
//		Mockito.when(venueService.getVenueDetails(1)).thenReturn(room);
//		ResponseEntity<?> actual=venueController.getDetails(1);
//		assertEquals(actual.getBody(),room);

	}

}

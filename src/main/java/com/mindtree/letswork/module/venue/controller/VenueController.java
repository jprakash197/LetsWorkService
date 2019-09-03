//package com.mindtree.letswork.module.venue.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mindtree.letswork.module.venue.dto.VenueDTO;
//import com.mindtree.letswork.module.venue.dto.VenueRequestDTO;
//import com.mindtree.letswork.module.venue.exception.ResourceNotFoundException;
//import com.mindtree.letswork.module.venue.service.serviceimp.VenueServiceImp;
//import com.mindtree.letswork.module.venue.util.DTOUtil;
//
//@CrossOrigin
//@RestController
//public class VenueController {
//
//	@Autowired
//	VenueServiceImp venueService;
//
//	@Autowired
//	DTOUtil dtoUtil;
//
//	@PostMapping("/Venues")
//	public ResponseEntity<List<VenueDTO>> getVenues(@Valid @RequestBody VenueRequestDTO details)
//			throws ResourceNotFoundException {
//		List<VenueDTO> venuesDto = new ArrayList<VenueDTO>();
//		venueService
//				.getFinalSearchedVenues(details.getVenueType(), details.getDate(), details.getCapacity(),
//						details.getCity())
//				.forEach(venue -> venuesDto.add((VenueDTO) dtoUtil.convert(venue, VenueDTO.class)));
//
//		return ResponseEntity.ok().body(venuesDto);
//
//	}
//
//	@GetMapping("/City")
//	public ResponseEntity<Set<String>> getCities() {
//		return ResponseEntity.ok().body(venueService.getCities());
//	}
//}

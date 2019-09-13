package com.mindtree.letswork.module.venue.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.venue.dto.VenueDTO;
import com.mindtree.letswork.module.venue.dto.VenueRequestDTO;
import com.mindtree.letswork.module.venue.entity.Image;
import com.mindtree.letswork.module.venue.entity.Venue;
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
	public ResponseEntity<?> getVenues(@Valid @RequestBody VenueRequestDTO details) throws VenueException {
		List<VenueDTO> venuesDto = new ArrayList<VenueDTO>();
		venueService
				.getFinalSearchedVenues(details.getVenueType(), details.getDate(), details.getCapacity(),
						details.getCity())
				.forEach(venue -> venuesDto.add((VenueDTO) dtoUtil.convert(venue, VenueDTO.class)));

		if (venuesDto.size() != 0)
			return ResponseEntity.ok().body(venuesDto);

		else
			return ResponseEntity.ok().body("No venues available currently");

	}

	@GetMapping("/cities")
	public ResponseEntity<Set<String>> getCities() {
		return ResponseEntity.ok().body(venueService.getCities());
	}

	@GetMapping("/getDetails/{id}")
	public VenueDTO getDetails(@PathVariable int id) throws VenueException {
		Venue venue = venueService.getVenueDetails(id);
		VenueDTO venueDto = (VenueDTO) dtoUtil.convert(venue, VenueDTO.class);
		List<String> photo = new ArrayList<>();
		Set<Image> image = venue.getImages();
		try {
			for (Image img : image) {
				byte[] encodeBase64 = Base64Utils.encode(img.getImage());
				String base64Encoded;

				base64Encoded = new String(encodeBase64, "UTF-8");
				String file = "data:image/jpg;base64," + base64Encoded;
				photo.add(file);
			}
			venueDto.setImage(photo);
		} catch (UnsupportedEncodingException e) {

			throw new VenueException(e.getMessage());
		}

		return venueDto;
	}
	
	@GetMapping("/venues")
	public List<VenueDTO> getAllVenues() throws VenueException {
		List<VenueDTO> venues = new ArrayList<VenueDTO>();
		
		StringBuilder exceptionMessage = new StringBuilder(); 
		
		this.venueService.getAllVenues().forEach(venue -> {
			List<String> photo = new ArrayList<>();
			Set<Image> image = venue.getImages();
			VenueDTO venueDto = null;
			try {
				for (Image img : image) {
					byte[] encodeBase64 = Base64Utils.encode(img.getImage());
					String base64Encoded;

					base64Encoded = new String(encodeBase64, "UTF-8");
					String file = "data:image/jpg;base64," + base64Encoded;
					photo.add(file);
				}
				venueDto = (VenueDTO) dtoUtil.convert(venue, VenueDTO.class);
				venueDto.setImage(photo);
			} catch (UnsupportedEncodingException e) {
				exceptionMessage.append(e.getMessage());
			}
			
			venues.add(venueDto);
		});
		
		if (exceptionMessage.length() != 0) {
			throw new VenueException(exceptionMessage.toString());
		}

		return venues;
	}
}

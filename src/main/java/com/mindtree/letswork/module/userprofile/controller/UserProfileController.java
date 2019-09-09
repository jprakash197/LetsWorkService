package com.mindtree.letswork.module.userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.authentication.dto.UserDTO;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.userprofile.Utils.DTOUtils;
import com.mindtree.letswork.module.userprofile.service.UserProfileService;

@RestController
@CrossOrigin
public class UserProfileController {

	@Autowired
	UserProfileService profileService;
	
	@Autowired
	DTOUtils utility;
	
	@GetMapping("/getUser/{name}")
	public UserDTO getUserByName(@PathVariable String name) {
		System.out.println(name);
		UserDTO currentUser = (UserDTO) utility.convert(profileService.getUserByName(name), UserDTO.class);
		return currentUser;
	}
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody UserDTO userDTO) {
		System.out.println(profileService.addUser((User) utility.convert(userDTO, User.class)));
	}
	
	@PutMapping("getUser/{name}")
	public void editEmail(@RequestBody String email, @PathVariable String name) {
		profileService.editUserEmail(email, name);
	}
	
}

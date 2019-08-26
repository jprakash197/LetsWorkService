package com.mindtree.letswork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.property.LetsWorkProperty;

@RestController
@CrossOrigin
public class HomeController {
	@Autowired
	private LetsWorkProperty letsWorkProperty; 
	
	@GetMapping("/")
	public String home() {
		return "Welcome to " + letsWorkProperty.getTitle() 
		+ " || PROJECT : " + letsWorkProperty.getProject()
		+ " || VERSION : " + letsWorkProperty.getVersion() 
		+ " || ENVIORNMENT : " + letsWorkProperty.getEnvironment();
	}
	
}

package com.mindtree.letswork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.property.LetsWorkDevProperty;

@RestController
@CrossOrigin
public class HomeController {
	@Autowired
	private LetsWorkDevProperty letsWorkDevProperty; 
	
	@GetMapping("/")
	public String home() {
		return "Welcome to " + letsWorkDevProperty.getTitle() 
		+ " || PROJECT : " + letsWorkDevProperty.getProject()
		+ " || VERSION : " + letsWorkDevProperty.getVersion() 
		+ " || ENVIORNMENT : " + letsWorkDevProperty.getEnvironment();
	}
	
}

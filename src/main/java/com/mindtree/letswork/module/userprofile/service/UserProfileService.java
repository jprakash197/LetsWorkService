package com.mindtree.letswork.module.userprofile.service;

import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;

/**
 * @author M1053435
 *
 */
@Service
public interface UserProfileService {

	User getUserByName(String name);

	void editUserEmail(String email, String name);
	
}

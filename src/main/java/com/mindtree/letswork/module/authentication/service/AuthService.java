package com.mindtree.letswork.module.authentication.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;

@Service
public interface AuthService {
	
	public User login (String username);
	public User signup (User user);
	public User authenticatePassword (String password, User user) throws Exception;
	public boolean checkValidityOfRefCode(String referralCode);
	@PreAuthorize("isAnonymous()")
	public boolean isUsernameAvailable(String username);
	public boolean updateToken(String token, User user);
}

package com.mindtree.letswork.module.authentication.service;

import org.springframework.stereotype.Service;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidJWTToken;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;

@Service
public interface AuthService {
	
	public User login (String username);
	public User signup (User user) throws InvalidReferralCodeException, InvalidInputException;
	public User authenticatePassword (String password, User user) throws IncorrectPasswordException;
	public boolean validateReferralCode(String referralCode) throws InvalidReferralCodeException;
	public boolean isUsernameAvailable(String username);
	public boolean updateToken(String token, User user) throws InvalidJWTToken;
}

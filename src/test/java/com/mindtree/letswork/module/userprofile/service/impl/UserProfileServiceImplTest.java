package com.mindtree.letswork.module.userprofile.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.repository.UserRepo;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceImplTest {

	@Mock
	UserRepo repo;
	
	@InjectMocks 
	UserProfileServiceImpl service;
	
	@Test
	public void getUserByNameTest() {
		List<User> randomListForTesting = new ArrayList<User>();
		User newUser = new User();
		newUser.setUserName("Itachiark");
		randomListForTesting.add(newUser);
		User newUser2 = new User();
		newUser2.setUserName("Jyoti");
		randomListForTesting.add(newUser2);
		when(repo.findAll()).thenReturn(randomListForTesting);
		User user = service.getUserByName("Itachiark");
		assertNotNull(user);
		assertEquals("Itachiark", user.getUserName());
		List<User> anotherUselessList = new ArrayList<User>();
		when(repo.findAll()).thenReturn(anotherUselessList);
		assertNull(service.getUserByName("Name"));
	}
	
	@Test
	public void editUserEmailTest() {
		List<User> randomListForTesting = new ArrayList<User>();
		User newUser = new User();
		newUser.setUserName("Itachiark");
		newUser.setEmail("arka.chanda@mindtree.com");
		randomListForTesting.add(newUser);
		User newUser2 = new User();
		newUser2.setUserName("Jyoti");
		newUser.setEmail("jyoti.prakash@mindtree.com");
		randomListForTesting.add(newUser2);
		when(repo.findAll()).thenReturn(randomListForTesting);
		String email = "arka@chanda.com";
		String name = "Itachiark";
		service.editUserEmail(email, name);
		User user = service.getUserByName(name);
		assertEquals(email, user.getEmail());
	}

}

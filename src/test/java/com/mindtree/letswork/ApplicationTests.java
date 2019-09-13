package com.mindtree.letswork;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.authentication.controller.AuthController;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.module.authentication.service.impl.AuthServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Mock
	AuthService authService;
	
	@Mock
	AuthController authController;
	
	@Before
	public void beforeTest() {
		authService = new AuthServiceImpl();
	}
	
	@Test
	public void contextLoads() {
		List<String> stuff = new ArrayList<>();
		stuff.add("words");
		stuff.add("thing");
		stuff.add("llama");
		stuff.add("perculiar");
		stuff = stuff.stream().map(s -> new String("ostrich")).collect(Collectors.toList());
		
		System.out.println(stuff);
		
		
	}
	
	@Test
	public void verifyUserIsNotAdmin() {
//		when(authService.login("manic mike")).thenReturn("user");
//		User user = Mockito.mock(User.class);
//		when(user.getRole()).thenRe
		
	}

}

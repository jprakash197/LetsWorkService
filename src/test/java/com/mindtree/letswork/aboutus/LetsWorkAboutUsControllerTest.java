package com.mindtree.letswork.aboutus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.letswork.module.details.controller.UserControl;
import com.mindtree.letswork.module.details.dto.UserDto;
import com.mindtree.letswork.module.details.entity.User;
import com.mindtree.letswork.module.details.repository.User1Repository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetsWorkAboutUsControllerTest {

	@Autowired
	private UserControl userControl;

	@MockBean
	private User1Repository userRepository;

	@Test
	public void getAllTest() {

		String str = "Example String";
		byte[] b = str.getBytes();
		User user = new User(101, "rajath", "bhargav@gmail.com", b, "8939206370");
		User user1 = new User(201, "bhargav", "bhargav@gmail.com", b, "8939206370");
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		UserDto userDto = new UserDto(101, "rajath", "bhargav@gmail.com", "data:image/jpg;base64,RXhhbXBsZSBTdHJpbmc=",
				"8939206370");
		UserDto userDto1 = new UserDto(201, "bhargav", "bhargav@gmail.com",
				"data:image/jpg;base64,RXhhbXBsZSBTdHJpbmc=", "8939206370");

		List<UserDto> userDtoList = new ArrayList<UserDto>();

		userDtoList.add(userDto);
		userDtoList.add(userDto1);
		when(userRepository.findAll()).thenReturn(userList);

		List<UserDto> check = userControl.getAll();

		assertEquals(userDtoList, check);

	}
}

package com.mindtree.letswork.module.authentication.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mindtree.letswork.module.authentication.dto.UserInputDTO;
import com.mindtree.letswork.module.authentication.dto.UserOutputDTO;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.exception.IncorrectPasswordException;
import com.mindtree.letswork.module.authentication.exception.InvalidInputException;
import com.mindtree.letswork.module.authentication.exception.InvalidReferralCodeException;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.module.venue.util.DTOUtil;
import com.mindtree.letswork.security.JwtCreator;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	AuthService service;

	@Autowired
	UserDetailsService detailsService;

	@Autowired
	JwtCreator creator;

	@Autowired
	DTOUtil dtoUtil;
	
//	@Autowired
//	AuthenticationManager auth; 

	@GetMapping("/login/{username}&{password}")
	public UserOutputDTO login(@Valid @PathVariable String username, @Valid @PathVariable String password) 
			throws IncorrectPasswordException, UsernameNotFoundException {
		
		User user = (User) detailsService.loadUserByUsername(username);
		user = service.authenticatePassword(password, user);
		String token = creator.generateJwtToken(user);
		service.updateToken(token, user);
		
		UserOutputDTO userOutput = new UserOutputDTO();
		userOutput.setToken(token);
		userOutput.setRole(user.getRole());
		return userOutput;
	}

	@PostMapping("/signup")
	public UserOutputDTO signup(@Valid @RequestBody UserInputDTO userDTO)
			throws IOException, InvalidReferralCodeException, InvalidInputException {
		User user = mapOntoUser(userDTO);
		String password = user.getPassword();
		user = service.signup(user);
		// updateExcel(user, password);
		UserOutputDTO userOutput = new UserOutputDTO();
		userOutput.setToken(user.getToken());
		userOutput.setRole(user.getRole());
		return userOutput;
	}
	
	public User mapOntoUser(UserInputDTO userDTO) {
		User user = new User(); 
		user.setUserName(userDTO.getUsername());
		user.setRealName(userDTO.getRealName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setReferredCode(userDTO.getReferredCode());
		return user;
	}

	private void updateExcel(User user, String password) throws IOException {

		Workbook wb = new HSSFWorkbook();
		OutputStream fileOut = new FileOutputStream("D:\\SpringbootStuff\\apache\\Output.csv");
		Sheet sheet = wb.getSheet("sheet");

		int rownum = sheet.getLastRowNum();
		Row row = sheet.createRow(rownum++);
		Cell cell1 = row.createCell(0);
		cell1.setCellValue((String) user.getReferralCode());
		Cell cell2 = row.createCell(1);
		cell2.setCellValue((String) user.getRealName());
		Cell cell3 = row.createCell(2);
		cell3.setCellValue((String) user.getUserName());
		Cell cell4 = row.createCell(3);
		cell4.setCellValue((String) user.getEmail());
		Cell cell5 = row.createCell(4);
		cell5.setCellValue((String) password);
		Cell cell6 = row.createCell(5);
		cell6.setCellValue((String) user.getReferredCode());
		Cell cell7 = row.createCell(6);
		cell7.setCellValue((String) user.getRole());
		Cell cell8 = row.createCell(7);
		cell8.setCellValue((String) user.getToken());

		wb.write(fileOut);
		fileOut.close();

	}
	
//	public void authentification(String username, String password) {
//		auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//	}
	
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}

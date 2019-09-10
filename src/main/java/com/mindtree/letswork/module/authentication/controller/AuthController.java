package com.mindtree.letswork.module.authentication.controller;

import java.io.FileNotFoundException;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.letswork.module.authentication.dto.UserInputDTO;
import com.mindtree.letswork.module.authentication.dto.UserOutputDTO;
import com.mindtree.letswork.module.authentication.entity.User;
import com.mindtree.letswork.module.authentication.service.AuthService;
import com.mindtree.letswork.module.venue.util.DTOUtil;
import com.mindtree.letswork.security.JwtCreator;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {

	@Autowired
	AuthService service;

	@Autowired
	UserDetailsService detailsService;

	@Autowired
	JwtCreator creator;

	@Autowired
	DTOUtil dtoUtil;

	@GetMapping("/login/{username}&{password}")
	public UserOutputDTO login(@Valid @PathVariable String username, @Valid @PathVariable String password)
			throws Exception {
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
	public UserOutputDTO signup(@Valid @RequestBody UserInputDTO userDTO) throws IOException {
		User user = (User) dtoUtil.convert(userDTO, User.class);
		String password = user.getPassword(); 
		user = service.signup(user);
		updateExcel(user, password);
		UserOutputDTO userOutput = new UserOutputDTO();
		userOutput.setToken(user.getToken());
		userOutput.setRole(user.getRole());
		return userOutput;
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

}

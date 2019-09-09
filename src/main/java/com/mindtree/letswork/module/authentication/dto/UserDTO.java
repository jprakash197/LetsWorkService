package com.mindtree.letswork.module.authentication.dto;

import java.util.List;

import com.mindtree.letswork.module.booking.dto.BookingDTO;
import com.mindtree.letswork.module.booking.entity.Payment;

public class UserDTO {

	private int referralCode;
	private String userName;
	private String realName;
	private String email;
	private String password;
	private String refferedCode;
	private String role;
	private String token;
	private List<BookingDTO> bookings;
	private Payment paymentInfo;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRefferedCode() {
		return refferedCode;
	}
	public void setRefferedCode(String refferedCode) {
		this.refferedCode = refferedCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Payment getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public UserDTO() {
		super();
	}
	public int getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(int referralCode) {
		this.referralCode = referralCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<BookingDTO> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}

	
}

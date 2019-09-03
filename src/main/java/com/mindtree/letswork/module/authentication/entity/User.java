package com.mindtree.letswork.module.authentication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mindtree.letswork.module.booking.entity.Booking;
import com.mindtree.letswork.module.booking.entity.Payment;

@Entity
@Table
@JsonIdentityInfo(
		generator = ObjectIdGenerators.IntSequenceGenerator.class, 
		property="id"
		) 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "20"),
    })
	@Column(name = "ref_code", unique = true, nullable = false)
	private long refCode;

	@Column(name = "username")
	private String userName;
	
	@Column(name = "realname")
	private String realName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "reffered")
	private String refferedCode;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "token")
	private String token;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;
	
	@OneToOne(mappedBy = "user")
	private Payment paymentInfo;

	public long getRefCode() {
		return refCode;
	}

	public void setRefCode(long refCode) {
		this.refCode = refCode;
	}

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

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Payment getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((paymentInfo == null) ? 0 : paymentInfo.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + (int) (refCode ^ (refCode >>> 32));
		result = prime * result + ((refferedCode == null) ? 0 : refferedCode.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (paymentInfo == null) {
			if (other.paymentInfo != null)
				return false;
		} else if (!paymentInfo.equals(other.paymentInfo))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (refCode != other.refCode)
			return false;
		if (refferedCode == null) {
			if (other.refferedCode != null)
				return false;
		} else if (!refferedCode.equals(other.refferedCode))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	} 

}

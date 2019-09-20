package com.mindtree.letswork.module.details.dto;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


public class UserDto {
	
	
	private int id;
	
	
	private String name;
	
	private String email_id;
	
	
    private String primaryImage;
	
	

	public UserDto(int id, String name, String email_id, String primaryImage, String phone_number) {
		super();
		this.id = id;
		this.name = name;
		this.email_id = email_id;
		this.primaryImage = primaryImage;
		this.phone_number = phone_number;
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email_id=" + email_id + ", primaryImage="
				+ primaryImage + ", phone_number=" + phone_number + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	@Column(name = "PHONE_NUMBER")
	private String phone_number;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}

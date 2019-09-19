package com.mindtree.letswork.module.details.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="User_details")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;
	
	@Column(unique = true, name = "USERNAME")
	private String name;
	
	@Column(name = "EMAIL_ID")
	private String email_id;
	
	@Lob
    private byte[] primaryImage;
	
	

	public User(int id, String name, String email_id, byte[] primaryImage, String phone_number) {
		super();
		this.id = id;
		this.name = name;
		this.email_id = email_id;
		this.primaryImage = primaryImage;
		this.phone_number = phone_number;
	}

	public byte[] getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(byte[] primaryImage) {
		this.primaryImage = primaryImage;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email_id=" + email_id + ", primaryImage="
				+ Arrays.toString(primaryImage) + ", phone_number=" + phone_number + "]";
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

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}

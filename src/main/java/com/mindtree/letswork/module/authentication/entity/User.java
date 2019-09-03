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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mindtree.letswork.module.booking.entity.Booking;

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
	
	@Column(name = "photo")
	private String photo;
	
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

}

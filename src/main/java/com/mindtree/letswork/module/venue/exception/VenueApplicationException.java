package com.mindtree.letswork.module.venue.exception;

import java.util.Date;

public class VenueApplicationException {

	private int status;
	private String message;
	private Date timestamp;
	private String details;
	
	public VenueApplicationException() {
		super();
	}

	public VenueApplicationException(int status, String message, Date timestamp, String details) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}

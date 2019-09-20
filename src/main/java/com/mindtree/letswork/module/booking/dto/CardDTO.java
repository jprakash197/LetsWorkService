package com.mindtree.letswork.module.booking.dto;

import javax.validation.constraints.Pattern;

public class CardDTO {

	private int cardId;
	
	@Pattern(regexp = "^(\\d{4}[- ]){3}\\d{4}|\\d{16}$", message = "Invalid card number")
	private String cardNumber;
	private String cardName;
	private String expiryDate;

	public CardDTO() {

	}

	public CardDTO(int cardId, String cardNumber, String cardName, String expiryDate) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.expiryDate = expiryDate;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}

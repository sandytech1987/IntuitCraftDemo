package com.sandeep.intuitcraftdemo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_DETAILS")
public class PaymentDetails implements Serializable {

	private static final long serialVersionUID = 3221680778535253529L;

	@Id
	@Column(name = "ACCOUNT_NUMBER")
	private Integer accountNumber;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Convert(converter = JpaAddressConverter.class)
	@Column(name = "ADDRESS", insertable = true, updatable = true, nullable = true, columnDefinition = "json")
	private Address address;

	@Convert(converter = JpaPurchaseConverter.class)
	@Column(name = "PURCHASE", insertable = true, updatable = true, nullable = true, columnDefinition = "json")
	private Purchase purchase;
	
	

	public PaymentDetails() {
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

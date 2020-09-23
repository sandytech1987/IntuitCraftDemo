package com.sandeep.intuitcraftdemo.model;

import java.io.Serializable;

public class InboundPayment implements Serializable{

	private static final long serialVersionUID = -1927963150433049984L;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private Address address;

	private Purchase purchase;

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

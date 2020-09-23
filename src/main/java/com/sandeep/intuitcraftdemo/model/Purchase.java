package com.sandeep.intuitcraftdemo.model;

import java.io.Serializable;

public class Purchase implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String item;
	private Double amount;
	
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}

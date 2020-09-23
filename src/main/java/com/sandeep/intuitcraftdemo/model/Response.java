package com.sandeep.intuitcraftdemo.model;

public class Response {

	private String message;
	private PaymentDetails paymentDetails;

	public Response(String message, PaymentDetails paymentDetails) {
		super();
		this.message = message;
		this.paymentDetails = paymentDetails;
	}

	public Response() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

}

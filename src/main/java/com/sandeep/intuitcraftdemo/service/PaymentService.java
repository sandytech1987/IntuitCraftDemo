package com.sandeep.intuitcraftdemo.service;

import java.lang.invoke.ConstantCallSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.intuitcraftdemo.model.InboundPayment;
import com.sandeep.intuitcraftdemo.model.PaymentDetails;
import com.sandeep.intuitcraftdemo.model.Response;
import com.sandeep.intuitcraftdemo.repository.PaymentRepository;
import com.sandeep.intuitcraftdemo.utils.Constants;
import java.util.Optional;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PaymentGatewayService paymentGatewayService;

	public Response processPayment(PaymentDetails paymentDetails) {

		boolean isValidPayment = isValidPayment(paymentDetails.getPurchase().getAmount());
		String message = "";
		
		if(paymentDetails.getAccountNumber() == null) {
			message = Constants.INVALID_MESSAGE;
		}else if (!isValidPayment) {
			message = Constants.INVALID_MESSAGE;
		} else if (paymentDetails.getAddress() == null) {
			message = Constants.FAILURE_MESSAGE;
		}
		message = Constants.SUCCESS_MESSAGE;

		InboundPayment inboundPayment = new InboundPayment();
		inboundPayment.setFirstName(paymentDetails.getFirstName());
		inboundPayment.setLastName(paymentDetails.getLastName());
		inboundPayment.setAddress(paymentDetails.getAddress());
		inboundPayment.setPurchase(paymentDetails.getPurchase());
		inboundPayment.setEmailAddress(paymentDetails.getEmailAddress());

		String result = paymentGatewayService.processPayment(inboundPayment);
		if (message.contentEquals(Constants.SUCCESS_MESSAGE) && result.contentEquals(Constants.SUCCESS_MESSAGE)) {
			Optional<PaymentDetails> payment = paymentRepository.findById(paymentDetails.getAccountNumber());
			if (!payment.isPresent()) {
				paymentRepository.save(paymentDetails);
			}
			return new Response(Constants.SUCCESS_MESSAGE, paymentDetails);
		} else if (message.contentEquals(Constants.INVALID_MESSAGE)) {
			return new Response(Constants.INVALID_MESSAGE, null);
		} else {
			return new Response(Constants.FAILURE_MESSAGE, null);
		}
	}

	boolean isValidPayment(Double amount) {
		if (amount == null) {
			return false;
		}
		return true;
	}

}

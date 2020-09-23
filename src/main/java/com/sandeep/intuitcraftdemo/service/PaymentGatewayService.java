package com.sandeep.intuitcraftdemo.service;

import org.springframework.stereotype.Service;

import com.sandeep.intuitcraftdemo.model.InboundPayment;
import com.sandeep.intuitcraftdemo.utils.Constants;

@Service
public class PaymentGatewayService {

	public String processPayment(InboundPayment inboundPayment) {

		if (inboundPayment.getPurchase() == null || inboundPayment.getPurchase().getAmount() == null) {
			return Constants.FAILURE_MESSAGE;
		}
		return Constants.SUCCESS_MESSAGE;

	}

}

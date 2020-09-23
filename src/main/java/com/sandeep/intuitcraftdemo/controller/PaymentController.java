package com.sandeep.intuitcraftdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.intuitcraftdemo.model.PaymentDetails;
import com.sandeep.intuitcraftdemo.model.Response;
import com.sandeep.intuitcraftdemo.service.PaymentService;

@RestController
@RequestMapping(value = "/api/v1")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping("/payment")
	public Response processPayment(@RequestBody PaymentDetails paymentDetails) {
		return paymentService.processPayment(paymentDetails);
	}

}

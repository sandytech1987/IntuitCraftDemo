package com.sandeep.intuitcraftdemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandeep.intuitcraftdemo.model.InboundPayment;
import com.sandeep.intuitcraftdemo.model.Response;
import com.sandeep.intuitcraftdemo.utils.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentGatewayServiceTest {

	@InjectMocks
	PaymentGatewayService paymentGatewayService;

	InboundPayment inboundPayment;
	Response responseObj;

	String sampleJson = "{\r\n" + "\"firstName\" : \"John\",\r\n" + "\"lastName\" : \"Doe\",\r\n"
			+ "\"emailAddress\" : \"john.doe@gmail.com\",\r\n" + "\"address\" : {\r\n"
			+ "\"street\" : \"1234 Given Lane\",\r\n" + "\"city\" : \"Toronto\",\r\n" + "\"province\" : \"ON\",\r\n"
			+ "\"country\" : \"Canada\"\r\n" + "},\r\n" + "\"purchase\":{\r\n"
			+ "\"item\" : \"1 year product subscription\",\r\n" + "\"amount\" : \"100.00\"\r\n" + "}\r\n" + "}\r\n"
			+ "";

	@Before
	public void setUp() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			inboundPayment = mapper.readValue(sampleJson, InboundPayment.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void processPaymentTest() {
		String result = paymentGatewayService.processPayment(inboundPayment);
		assertEquals(Constants.SUCCESS_MESSAGE, result);
	}

	@Test
	public void processPaymentInvalidTest() {
		inboundPayment.getPurchase().setAmount(null);
		String result = paymentGatewayService.processPayment(inboundPayment);
		assertEquals(Constants.FAILURE_MESSAGE, result);
	}
}

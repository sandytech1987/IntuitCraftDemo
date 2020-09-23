package com.sandeep.intuitcraftdemo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandeep.intuitcraftdemo.model.PaymentDetails;
import com.sandeep.intuitcraftdemo.model.Response;
import com.sandeep.intuitcraftdemo.repository.PaymentRepository;
import com.sandeep.intuitcraftdemo.utils.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentServiceTest {

	@InjectMocks
	PaymentService paymentService;

	@Mock
	PaymentGatewayService paymentGatewayService;

	@Mock
	PaymentRepository paymentRepository;

	PaymentDetails paymentDetails;
	Response responseObj;

	String sampleJson = "{\r\n" + "\"accountNumber\" : 12233,   \r\n" + "\"firstName\" : \"John\",\r\n"
			+ "\"lastName\" : \"Doe\",\r\n" + "\"emailAddress\" : \"john.doe@gmail.com\",\r\n" + "\"address\" : {\r\n"
			+ "\"street\" : \"1234 Given Lane\",\r\n" + "\"city\" : \"Toronto\",\r\n" + "\"province\" : \"ON\",\r\n"
			+ "\"country\" : \"Canada\"\r\n" + "},\r\n" + "\"purchase\":{\r\n"
			+ "\"item\" : \"1 year product subscription\",\r\n" + "\"amount\" : \"100.00\"\r\n" + "}\r\n" + "}\r\n"
			+ "";

	String responseString = "{\r\n" + "    \"message\": \"Payment processed successfully\",\r\n"
			+ "    \"paymentDetails\": {\r\n" + "        \"accountNumber\": 12233,\r\n"
			+ "        \"firstName\": \"John\",\r\n" + "        \"lastName\": \"Doe\",\r\n"
			+ "        \"emailAddress\": \"john.doe@gmail.com\",\r\n" + "        \"address\": {\r\n"
			+ "            \"street\": \"1234 Given Lane\",\r\n" + "            \"city\": \"Toronto\",\r\n"
			+ "            \"province\": \"ON\",\r\n" + "            \"country\": \"Canada\"\r\n" + "        },\r\n"
			+ "        \"purchase\": {\r\n" + "            \"item\": \"1 year product subscription\",\r\n"
			+ "            \"amount\": 100.0\r\n" + "        }\r\n" + "    }\r\n" + "}";

	@Before
	public void setUp() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			paymentDetails = mapper.readValue(sampleJson, PaymentDetails.class);
			responseObj = mapper.readValue(responseString, Response.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void isValidPaymentTest() {
		Double amount = 150.00;
		assertTrue(paymentService.isValidPayment(amount));
	}

	@Test
	public void isInvalidPaymentTest() {
		Double amount = null;
		assertFalse(paymentService.isValidPayment(amount));
	}

	@Test
	public void processPaymentTest() {
		Mockito.when(paymentGatewayService.processPayment(Mockito.any())).thenReturn(Constants.SUCCESS_MESSAGE);
		Response response = paymentService.processPayment(paymentDetails);
		assertEquals(responseObj.getMessage(), response.getMessage());
	}

	@Test
	public void processPaymentInvalidTest() {
		Mockito.when(paymentGatewayService.processPayment(Mockito.any())).thenReturn(Constants.INVALID_MESSAGE);
		Response response = paymentService.processPayment(paymentDetails);
		assertNotEquals(responseObj, response);
	}

}

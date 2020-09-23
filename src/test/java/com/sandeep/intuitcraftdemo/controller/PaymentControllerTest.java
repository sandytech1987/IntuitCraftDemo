package com.sandeep.intuitcraftdemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandeep.intuitcraftdemo.model.PaymentDetails;
import com.sandeep.intuitcraftdemo.model.Response;
import com.sandeep.intuitcraftdemo.service.PaymentService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = PaymentController.class)
@WithMockUser
public class PaymentControllerTest {

	String sampleJson = "{\r\n" + 
			"\"accountNumber\" : 12233,   \r\n" + 
			"\"firstName\" : \"John\",\r\n" + 
			"\"lastName\" : \"Doe\",\r\n" + 
			"\"emailAddress\" : \"john.doe@gmail.com\",\r\n" + 
			"\"address\" : {\r\n" + 
			"\"street\" : \"1234 Given Lane\",\r\n" + 
			"\"city\" : \"Toronto\",\r\n" + 
			"\"province\" : \"ON\",\r\n" + 
			"\"country\" : \"Canada\"\r\n" + 
			"},\r\n" + 
			"\"purchase\":{\r\n" + 
			"\"item\" : \"1 year product subscription\",\r\n" + 
			"\"amount\" : \"100.00\"\r\n" + 
			"}\r\n" + 
			"}\r\n" + 
			"";

	String responseString = "{\r\n" + 
			"    \"message\": \"Payment processed successfully\",\r\n" + 
			"    \"paymentDetails\": {\r\n" + 
			"        \"accountNumber\": 12233,\r\n" + 
			"        \"firstName\": \"John\",\r\n" + 
			"        \"lastName\": \"Doe\",\r\n" + 
			"        \"emailAddress\": \"john.doe@gmail.com\",\r\n" + 
			"        \"address\": {\r\n" + 
			"            \"street\": \"1234 Given Lane\",\r\n" + 
			"            \"city\": \"Toronto\",\r\n" + 
			"            \"province\": \"ON\",\r\n" + 
			"            \"country\": \"Canada\"\r\n" + 
			"        },\r\n" + 
			"        \"purchase\": {\r\n" + 
			"            \"item\": \"1 year product subscription\",\r\n" + 
			"            \"amount\": 100.0\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"}";

	
	@MockBean
	PaymentService paymentService;
	
	PaymentDetails paymentDetails;
	Response response;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			paymentDetails = mapper.readValue(sampleJson, PaymentDetails.class);
			response = mapper.readValue(responseString, Response.class);
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testProcessPayment() {
		Mockito.when(paymentService.processPayment(Mockito.any())).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/payment")
				.accept(MediaType.APPLICATION_JSON).content(sampleJson).contentType(MediaType.APPLICATION_JSON);

		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();

			int status = response.getStatus();
			String response1 = response.getContentAsString();

			assertEquals(HttpStatus.OK.value(), status);
			JSONAssert.assertEquals(responseString, response1, false);
		} catch (Exception e) {
		}
	}

}

/**
 * 
 */
package com.bank.account.ti.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.bank.account.model.dto.OperationsHistoryResponse;
import com.bank.account.ti.controller.common.AbstractCommonTiTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OperationControllerTiTest extends AbstractCommonTiTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * Make a successful request by adding all required header and body request.
	 * 
	 * @throws Exception
	 */
	@Test
	@Order(1)
	final void testPerformDepositOrWithdrawal_isCreated() throws Exception {
		String content = objectMapper.writeValueAsString(OPERATION_REQUEST_OK);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", USER_NAME)//
				.header("accountNumber", ACCOUNT_NUMBER)//
				.content(content);

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isCreated());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		Assert.isTrue(contentAsString.contains("\"operationId\":1"), "Json does nont contain operationId");
	}

	/**
	 * 
	 * Checks that GET return a list of operations and 200-Ok.
	 * 
	 * @throws Exception
	 */
	@Test
	@Order(2)
	final void testGetOperationsHistory_isOk() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", USER_NAME)//
				.header("accountNumber", ACCOUNT_NUMBER)//
				.param("searchBy", "operationId:1");

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		OperationsHistoryResponse returnResponse = objectMapper.readValue(contentAsString,
				OperationsHistoryResponse.class);

		Assert.notNull(returnResponse,"returnResponse is Null");
	}
	
	
	/**
	 * Make a bad request by passing bad header and body request.
	 * 
	 * @throws Exception
	 */
	@Test
	@Order(3)
	final void testPerformDepositOrWithdrawal_BadHeaders_isBadRequest_() throws Exception {
		String content = objectMapper.writeValueAsString(OPERATION_REQUEST_OK);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", "userNotFound")//
				.header("accountNumber", "9999")//
				.content(content);

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isBadRequest());
	}
	
	/**
	 * Make a bad request by passing body request with max withdrawal.
	 * 
	 * @throws Exception
	 */
	@Test
	@Order(4)
	final void testPerformDepositOrWithdrawal_MaxWithdrawal_isBadRequest_() throws Exception {
		String content = objectMapper.writeValueAsString(MAX_WITHDRAWAL_REQUEST_KO);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", USER_NAME)//
				.header("accountNumber", ACCOUNT_NUMBER)//
				.content(content);

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isBadRequest());
	}

}

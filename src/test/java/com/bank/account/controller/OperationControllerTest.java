package com.bank.account.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bank.account.common.AbstractCommonTest;
import com.bank.account.controller.common.CustomizedSearchCriteria;
import com.bank.account.controller.common.CustomizedSortCriteria;
import com.bank.account.model.dto.OperationPostRequest;
import com.bank.account.model.dto.OperationResponse;
import com.bank.account.model.dto.OperationsHistoryResponse;
import com.bank.account.model.dto.common.Errors;
import com.bank.account.service.OperationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OperationController.class)
@TestPropertySource(locations = {"classpath:errors.properties"})
class OperationControllerTest extends AbstractCommonTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OperationService service;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Make a successful request by add adding all required header and body request.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testPerformDepositOrWithdrawal_isCreated() throws Exception {
		OperationResponse referenceResponse = getOperationResponse();
		when(service.performDepositOrWithdrawal(any(OperationPostRequest.class), any(String.class), any(Long.class)))
				.thenReturn(referenceResponse);

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

		OperationResponse returnResponse = objectMapper.readValue(contentAsString, OperationResponse.class);

		assertThat(referenceResponse).isEqualTo(returnResponse);
	}

	/**
	 * 
	 * Generate a bad request error with a list of Format error when the body
	 * request is empty.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testPerformDepositOrWithdrawal_isBadRequest_ifNoBody() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", USER_NAME)//
				.header("accountNumber", ACCOUNT_NUMBER)//
				.content("{}");

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isBadRequest());
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		String returnJsonErrors = "{\"errors\":["
				+ "{\"errorCode\":400,\"errorOrigin\":\"Format error\",\"errorMessage\":\"description: must not be blank\"},"//
				+ "{\"errorCode\":400,\"errorOrigin\":\"Format error\",\"errorMessage\":\"amount: must not be null\"},"//
				+ "{\"errorCode\":400,\"errorOrigin\":\"Format error\",\"errorMessage\":\"description: must not be null\"}"//
				+ "]}";//
		Errors referenceErrors = objectMapper.readValue(returnJsonErrors, Errors.class);

		Errors returnErrors = objectMapper.readValue(contentAsString, Errors.class);

		assertThat(referenceErrors).isEqualTo(returnErrors);
	}

	/**
	 * Generate a server Error by passing a String value instead of a numeric value
	 * in the accountNumber header.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testPerformDepositOrWithdrawal_is5xxServerError() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.POST, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("accountNumber", "ACCOUNT_NUMBER");

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().is5xxServerError());
	}

	/**
	 * 
	 * Checks that GET return a list of operations and 200-Ok.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testGetOperationsHistory_isOk() throws Exception {
		OperationsHistoryResponse operationsHistoryResponse = getOperationsHistoryResponse();

		when(service.getAllOperationsHistory(any(String.class), any(Long.class), any(int.class), any(int.class),
				any(CustomizedSortCriteria.class), nullable(CustomizedSearchCriteria.class))).thenReturn(operationsHistoryResponse);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", USER_NAME)//
				.header("accountNumber", ACCOUNT_NUMBER);

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		OperationsHistoryResponse returnResponse = objectMapper.readValue(contentAsString,
				OperationsHistoryResponse.class);

		assertThat(operationsHistoryResponse).isEqualTo(returnResponse);
	}

	/**
	 * 
	 * Checks that GET return an error and 400-BadRequest if searchBy contains invalid field.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testGetOperationsHistory_isBadRequest() throws Exception {
		OperationsHistoryResponse operationsHistoryResponse = getOperationsHistoryResponse();

		when(service.getAllOperationsHistory(any(String.class), any(Long.class), any(int.class), any(int.class),
				any(CustomizedSortCriteria.class), nullable(CustomizedSearchCriteria.class))).thenReturn(operationsHistoryResponse);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(HttpMethod.GET, URL_OPERATIONS)//
				.contentType(MediaType.APPLICATION_JSON)//
				.header("userName", USER_NAME)//
				.header("accountNumber", ACCOUNT_NUMBER)//
				.param("searchBy", "operation:Deposit");

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andDo(print());

		resultActions.andExpect(status().isBadRequest());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		String returnJsonErrors = "{\"errors\":["//
				+ "{\"errorCode\":400,"//
				+ "\"errorOrigin\":\"Functional error\","//
				+ "\"errorMessage\":\"1004 - Invalid field operation in search and sort.\"}"//
				+ "]}";//
		Errors referenceErrors = objectMapper.readValue(returnJsonErrors, Errors.class);

		Errors returnErrors = objectMapper.readValue(contentAsString, Errors.class);

		assertThat(referenceErrors).isEqualTo(returnErrors);
	}
	
}

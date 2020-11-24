package com.bank.account.ti.controller.common;

import com.bank.account.model.dto.OperationPostRequest;

public abstract class AbstractCommonTiTest {
	protected static final String URL_OPERATIONS = "/v1/operations";
	protected static final String USER_NAME = "dtrump";
	protected static final Long ACCOUNT_NUMBER = 1000L;
	protected static final Double AMOUNT = 15.02;
	protected static final String DESCRIPTION = "Personal deposit";
	
	protected static final OperationPostRequest OPERATION_REQUEST_OK = new OperationPostRequest(DESCRIPTION, AMOUNT);
	
	
	protected static final Double MAX_AMOUNT_WITHDRAWAL = -987654321.19;
	protected static final String MAX_DESCRIPTION_WITHDRAWAL = "Max withdrawal";
	protected static final OperationPostRequest MAX_WITHDRAWAL_REQUEST_KO = new OperationPostRequest(MAX_DESCRIPTION_WITHDRAWAL, MAX_AMOUNT_WITHDRAWAL);
}


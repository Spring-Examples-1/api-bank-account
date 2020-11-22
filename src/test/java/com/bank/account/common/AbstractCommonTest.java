/**
 * 
 */
package com.bank.account.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bank.account.model.dto.OperationPostRequest;
import com.bank.account.model.dto.OperationResponse;
import com.bank.account.model.dto.OperationsHistoryResponse;
import com.bank.account.model.entity.Account;
import com.bank.account.model.entity.Client;
import com.bank.account.model.entity.Operation;
import com.bank.account.util.AccountTypeEnum;
import com.bank.account.util.DateUtis;
import com.bank.account.util.OperationTypeEnum;

/**
 * @author Rached
 *
 */
public abstract class AbstractCommonTest {

	protected static final String URL_OPERATIONS = "/v1/operations";
	protected static final String USER_NAME = "userName";
	protected static final long ACCOUNT_ID = 1;
	protected static final Long ACCOUNT_NUMBER = 1000L;
	protected static final Long OPERATION_ID_1 = 1L;
	protected static final Double AMOUNT = 15.02;
	protected static final String DESCRIPTION = "Personal transfer";
	protected static final LocalDateTime DATE_TIME_OPERATION = DateUtis.formatDateTime("2020-11-20 12:00:00");
	protected static final Double BALANCE = 10000.23;

	protected static final OperationPostRequest OPERATION_REQUEST_OK = new OperationPostRequest(DESCRIPTION, AMOUNT);
	protected static final Client CLIENT_1 = new Client(1, USER_NAME, "FIRST NAME", "LAST NAME");
	protected static final Account ACCOUNT_1_CLIENT_1 = new Account(ACCOUNT_ID, AccountTypeEnum.SAVINGS_ACCOUNTS,
			ACCOUNT_NUMBER, BALANCE, CLIENT_1);
	protected static final Operation OPERATION_ENTITY_1 = new Operation(OPERATION_ID_1, AMOUNT, DESCRIPTION,
			DATE_TIME_OPERATION, ACCOUNT_1_CLIENT_1);

	/**
	 * @return
	 */
	protected static final OperationsHistoryResponse getOperationsHistoryResponse() {
		OperationsHistoryResponse operationsHistory = new OperationsHistoryResponse(BALANCE, getOperations());
		return operationsHistory;
	}

	/**
	 * @return
	 */
	protected static final List<OperationResponse> getOperations() {
		List<OperationResponse> operations = new ArrayList<>();
		operations.add(getOperationResponse());
		return operations;
	}

	/**
	 * @return
	 */
	protected static final Page<Operation> getPage() {
		Pageable pageable = PageRequest.of(0, 10);
		List<Operation> Operations = new ArrayList<>() ;
		Operations.add(OPERATION_ENTITY_1);
		Page<Operation> page = new PageImpl<>(Operations, pageable, 0);
		return page;
	}
	
	/**
	 * @return
	 */
	protected static final OperationResponse getOperationResponse() {
		OperationResponse operation = new OperationResponse();
		operation.setOperationId(OPERATION_ID_1);
		operation.setAmount(AMOUNT);
		operation.setDate(DATE_TIME_OPERATION);
		operation.setOperation(OperationTypeEnum.DEPOSIT_OPERATION.toString());
		return operation;
	}

}

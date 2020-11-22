package com.bank.account.service;

import com.bank.account.controller.common.CustomizedSearchCriteria;
import com.bank.account.controller.common.CustomizedSortCriteria;
import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.OperationPostRequest;
import com.bank.account.model.dto.OperationResponse;
import com.bank.account.model.dto.OperationsHistoryResponse;

/**
 * @author Rached
 *
 */
public interface OperationService {

	/**
	 * Performs a deposit or withdrawal for an account if all the functional check
	 * are passed
	 * 
	 * @param postRequest   The {@link OperationPostRequest}
	 * @param userName      The user account
	 * @param accountNumber The account number
	 * @return The operation DTO
	 * @throws FunctionalException
	 */
	OperationResponse performDepositOrWithdrawal(OperationPostRequest postRequest, String userName, Long accountNumber)
			throws FunctionalException;

	/**
	 * 
	 * Checks and prepares response for get all operations.
	 * 
	 * @param userName      The user name
	 * @param accountNumber The account number
	 * @param page          The page number, zero-based page index.
	 * @param size          The size of the page to be returned.
	 * @param customizedSortCriteria        The customized sort criteria
	 * @param customizedSearchCriteria      The customized search criteria
	 * @return The {@link OperationsHistoryResponse}
	 * @throws FunctionalException
	 */
	OperationsHistoryResponse getAllOperationsHistory(String userName, Long accountNumber, int page, int size,
			CustomizedSortCriteria customizedSortCriteria, CustomizedSearchCriteria customizedSearchCriteria) throws FunctionalException;

}

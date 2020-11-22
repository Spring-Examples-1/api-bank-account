package com.bank.account.service.core;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.entity.Account;

public interface CommonService{

	/**
	 * 
	 * Checks user and account validity before any operations.
	 * 
	 * @param userName      The user name
	 * @param accountNumber The account number
	 * @return The {@link Account} entity.
	 * @throws FunctionalException if one or many common checks are failed.
	 */
	Account checksBeforeAllOperation(String userName, Long accountNumber) throws FunctionalException;

	
}

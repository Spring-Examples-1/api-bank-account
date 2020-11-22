package com.bank.account.service;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.entity.Account;
import com.bank.account.util.ApiErrors;

public interface AccountService {

	/**
	 * Checks if we have a account in the data base with the given account number.
	 * 
	 * @param accountNumber
	 * 		The account number
	 * @param apiErrors
	 * 		The {@link ApiErrors}
	 * @return
	 * 		{@link Account} entity associated to account number
	 * @throws FunctionalException
	 */
	Account checkIsValidAccount(Long accountNumber, ApiErrors apiErrors) throws FunctionalException;

}

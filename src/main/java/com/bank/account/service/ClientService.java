package com.bank.account.service;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.entity.Client;
import com.bank.account.util.ApiErrors;

public interface ClientService {

	/**
	 * Checks if we have a client in the data base with the given user name.
	 * 
	 * @param userName
	 * 		The user name
	 * @param apiErrors
	 * 		The {@link ApiErrors}
	 * @return 
	 * 		{@link Client} entity associated to user name
	 * @throws FunctionalException
	 */
	Client checkIsValidClient(String userName, ApiErrors apiErrors) throws FunctionalException;

}

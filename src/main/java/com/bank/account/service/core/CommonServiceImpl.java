package com.bank.account.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.entity.Account;
import com.bank.account.service.AccountService;
import com.bank.account.service.ClientService;
import com.bank.account.util.ApiErrors;
import com.bank.account.util.ApiUtils;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientService clientService;

	
	@Override
	public Account checksBeforeAllOperation(String userName, Long accountNumber) throws FunctionalException {
		ApiErrors apiErrors = new ApiErrors();

		clientService.checkIsValidClient(userName, apiErrors);
		Account account = accountService.checkIsValidAccount(accountNumber, apiErrors);
		ApiUtils.checkIsUserAccount(account, userName, apiErrors);

		apiErrors.throwExceptions();

		return account;
	}
	
	
}

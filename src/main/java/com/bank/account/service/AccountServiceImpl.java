package com.bank.account.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.entity.Account;
import com.bank.account.repository.AccountRepository;
import com.bank.account.util.ApiErrors;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account checkIsValidAccount(Long accountNumber, ApiErrors apiErrors) throws FunctionalException {
		if (Objects.isNull(accountNumber)) {
			apiErrors.initErrorAndThrow(2001, "accountNumber");
			return null;
		}
		Optional<Account> returnEntity = accountRepository.findByAccountNumber(accountNumber);
		if (returnEntity.isPresent()) {
			return returnEntity.get();
		}else {
			apiErrors.addError(1001);
			return null;
		}
	}
}

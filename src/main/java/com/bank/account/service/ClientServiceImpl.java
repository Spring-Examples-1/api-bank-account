package com.bank.account.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.entity.Client;
import com.bank.account.repository.ClientRepository;
import com.bank.account.util.ApiErrors;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client checkIsValidClient(String userName, ApiErrors apiErrors) throws FunctionalException {
		if (Objects.isNull(userName)) {
			apiErrors.initErrorAndThrow(2001, "userName");
			return null;
		}
		Optional<Client> returnEntity = clientRepository.findByUserName(userName);
		if (returnEntity.isPresent()) {
			return returnEntity.get();
		}else {
			apiErrors.addError(1000);
			return null;
		}
	}
}

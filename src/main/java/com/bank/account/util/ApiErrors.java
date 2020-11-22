package com.bank.account.util;

import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.common.Errors;

public class ApiErrors {
	
	private Errors errors = null;

	public ApiErrors initApiErrors() {
		errors = new Errors(); 
		return this;
	}
	
	public Errors getErrors() {
		return errors;
	}

	public void throwExceptions() throws FunctionalException {
		if (Objects.nonNull(errors) && !CollectionUtils.isEmpty(errors.getErrors())) {
			throw new FunctionalException(getErrors());
		}
		
	}
	
	public void addError(Integer errorCode, Object... params) {
		if (Objects.isNull(errors)) {
			errors = new Errors();
		}
		errors.addError(errorCode, params);
	}

	public void initErrorAndThrow(Integer errorCode, Object... params) throws FunctionalException {
		errors = new Errors();
		errors.addError(errorCode, params);
		throwExceptions();
	}

}

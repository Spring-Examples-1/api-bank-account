package com.bank.account.util;

import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.common.Errors;

/**
 * @author Rached
 *
 */
public class ApiErrors {
	
	private Errors errors = null;

	/**
	 * Initializes the of ApiErrors attributes
	 * @return
	 */
	public ApiErrors initApiErrors() {
		errors = new Errors(); 
		return this;
	}
	
	/**
	 * @return
	 */
	public Errors getErrors() {
		return errors;
	}

	/**
	 * Throws a FunctionalException if the errors list is not empty
	 * @throws FunctionalException
	 */
	public void throwExceptions() throws FunctionalException {
		if (Objects.nonNull(errors) && !CollectionUtils.isEmpty(errors.getErrorsList())) {
			throw new FunctionalException(getErrors());
		}
		
	}
	
	/**
	 * Adds new error to the errors list
	 * @param errorCode
	 * @param params
	 */
	public void addError(Integer errorCode, Object... params) {
		if (Objects.isNull(errors)) {
			errors = new Errors();
		}
		errors.addError(errorCode, params);
	}

	/**
	 * @param errorCode
	 * @param params
	 * @throws FunctionalException
	 */
	public void initErrorAndThrow(Integer errorCode, Object... params) throws FunctionalException {
		errors = new Errors();
		errors.addError(errorCode, params);
		throwExceptions();
	}

}

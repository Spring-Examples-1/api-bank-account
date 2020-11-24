package com.bank.account.model.dto.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Rached
 *
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Errors {

	/**
	 * The error list
	 */
	@JsonProperty(value = "errors")
	private List<Error> errorsList;

	/**
	 * A costumer constructor with errorCode, errorOrigin and errorMessage
	 * 
	 * @param errorCode  
	 * 		The error code
	 * @param errorOrigin
	 * 		The error origin
	 * @param errorMessage    
	 * 		The error message
	 */
	public Errors(Integer errorCode, String errorOrigin, String errorMessage) {
		super();
		this.errorsList = Collections.singletonList(new Error(errorCode,  errorOrigin, errorMessage));
	}
	
	/**
	 * Add new error in error list
	 * 
	 * @param errorCode
	 * 		The error code
	 * @param errorOrigin
	 * 		The error origin
	 * @param errorMessage
	 * 		The error message
	 */
	public void addError(Integer errorCode, String errorOrigin, String errorMessage) {
		if (errorsList == null) {
			errorsList = new ArrayList<>();
		}
		errorsList.add(new Error(errorCode, errorOrigin, errorMessage));
	}

	/**
	 * Add new error in error list with error code and parameters
	 * 
	 * @param errorCode
	 * 		The error code
	 * @param params
	 * 		The list of parameters for a configured error
	 */
	public void addError(Integer errorCode, Object[] params) {
		if (errorsList == null) {
			errorsList = new ArrayList<>();
		}
		errorsList.add(new Error(errorCode, params));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Errors other = (Errors) obj;
		if (errorsList == null) {
			if (other.errorsList != null)
				return false;
		} else if (!CollectionUtils.isEqualCollection(errorsList, other.errorsList))
			return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorsList == null) ? 0 : errorsList.hashCode());
		return result;
	}
	
}

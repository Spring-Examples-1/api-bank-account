package com.bank.account.model.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rached
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

	
	/**
	 * The error code
	 */
    private Integer errorCode;
    
    /**
	 * The error origin
	 */
    private String errorOrigin;
    
	/**
	 * The error message
	 */
	@JsonProperty(value = "errorMessage")
    private String errorMessage;
	
	/**
	 * The error params
	 */
	@JsonIgnore
    private Object[] params;

	public Error(Integer errorCode, Object[] params) {
		super();
		this.errorCode = errorCode;
		this.params = params;
	}
	
	public Error(Integer errorCode, String errorOrigin,  String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorOrigin = errorOrigin;
		this.errorMessage = errorMessage;
	}
	
}

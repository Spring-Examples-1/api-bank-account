package com.bank.account.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Class used for passing a Json object when posting a new operation request
 * 
 * @author Rached
 *
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OperationPostRequest {

	/**
	 * The operation description
	 */
	@NotNull
	@NotBlank
	@Size(max=50)
	@JsonProperty(value = "description")
	private String description;

	/**
	 * The operation amount
	 */
	@NotNull
	@JsonProperty(value = "amount")
    @Digits(integer=10, fraction=2)
	private Double amount;
	

}

package com.bank.account.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.controller.common.AbstractCommonController;
import com.bank.account.controller.common.CustomizedSearchCriteria;
import com.bank.account.controller.common.CustomizedSortCriteria;
import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.OperationPostRequest;
import com.bank.account.model.dto.OperationResponse;
import com.bank.account.model.dto.OperationsHistoryResponse;
import com.bank.account.service.OperationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest controller manages the Deposit, Withdrawal for a user account and checks
 * the history operations.
 * 
 * @author Rached
 *
 */
@RestController
@RequestMapping("/v1/operations")
@Api(tags = {
		"API for a bank account management." }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OperationController extends AbstractCommonController{

	@Autowired
	private OperationService operationService;

	/**
	 * Performs a deposit or withdrawal for an account if all the functional check
	 * are passed.
	 * 
	 * @param userName      The user name
	 * @param accountNumber the account number
	 * @param postRequest   The {@link OperationPostRequest}
	 * @return The {@link ResponseEntity}
	 * @throws FunctionalException
	 */
	@ApiOperation(value = "Make operations of Deposit and Withdrawal", response = OperationResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperationResponse> performDepositOrWithdrawal(
			@RequestHeader(name = "userName", required = true) String userName,
			@RequestHeader(name = "accountNumber", required = true) Long accountNumber,
			@Validated @RequestBody OperationPostRequest postRequest) throws FunctionalException {

		OperationResponse operation = operationService.performDepositOrWithdrawal(postRequest, userName, accountNumber);

		return new ResponseEntity<>(operation, HttpStatus.CREATED);
	}

	/**
	 * Gets all operations in a user account.
	 * 
	 * @param userName      The user name
	 * @param accountNumber the account number
	 * @param page          The page number, zero-based page index.
	 * @param size          The size of the page to be returned.
	 * @param sortBy        The sort by field name list in the format "(+|-) + (fieldName) +(,)"
	 * @param searchBy      The search by field name list in the format "(fieldName) + (:|<|>|) + (value) + (,)"
	 *                      value
	 * @return The {@link OperationsHistoryResponse}
	 * @throws FunctionalException
	 */
	@ApiOperation(value = "View a list of Operations History")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperationsHistoryResponse> getAllOperationsHistory(
			@RequestHeader(name = "userName", required = true) String userName,
			@RequestHeader(name = "accountNumber", required = true) Long accountNumber,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "date") String sortBy,
			@RequestParam(value = "searchBy", required = false) String searchBy) throws FunctionalException {

		CustomizedSortCriteria customizedSortCriteria = toSortCriteria(sortBy);
		CustomizedSearchCriteria customizedSearchCriteria = toSearchCriteria(searchBy);
		
		OperationsHistoryResponse operationsHistory = operationService.getAllOperationsHistory(userName, accountNumber,
				page, size, customizedSortCriteria, customizedSearchCriteria);

		return new ResponseEntity<>(operationsHistory, HttpStatus.OK);
	}
	
	@Override
	protected List<String> getAuthorizedFields() {
		return Arrays.asList("operationId","amount","date");
	}

}

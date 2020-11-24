package com.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.controller.common.CustomizedSearchCriteria;
import com.bank.account.controller.common.CustomizedSortCriteria;
import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.OperationPostRequest;
import com.bank.account.model.dto.OperationResponse;
import com.bank.account.model.dto.OperationsHistoryResponse;
import com.bank.account.model.entity.Account;
import com.bank.account.model.entity.Operation;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.OperationRepository;
import com.bank.account.service.core.AbstractSearchService;
import com.bank.account.service.core.CommonService;
import com.bank.account.util.ApiErrors;
import com.bank.account.util.ApiUtils;

/**
 * @author Rached
 *
 */
@Service
public class OperationServiceImpl extends AbstractSearchService<Operation> implements OperationService {

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OperationResponse performDepositOrWithdrawal(OperationPostRequest postRequest, String userName,
			Long accountNumber) throws FunctionalException {

		/*** Common checks before every deposit or withdrawal ***/
		Account account = commonService.checksBeforeAllOperation(userName, accountNumber);

		/*** Specific checks */
		ApiErrors apiErrors = new ApiErrors();
		ApiUtils.checkIsAcceptedWithdrawalOperation(account, postRequest.getAmount(), apiErrors);
		apiErrors.throwExceptions();

		/*** Update account and save a new operation *****/
		account = updateBalanceAccount(postRequest.getAmount(), account);

		Operation operation = createOperation(postRequest, account);

		return new OperationResponse(operation);
	}

	@Override
	public OperationsHistoryResponse getAllOperationsHistory(String userName, Long accountNumber, int page, int size,
			CustomizedSortCriteria customizedSortCriteria, CustomizedSearchCriteria customizedSearchCriteria) throws FunctionalException {
		/*** Checks before every get operations ***/
		Account account = commonService.checksBeforeAllOperation(userName, accountNumber);

		/*** Prepare GET response object */
		OperationsHistoryResponse response = new OperationsHistoryResponse();
		response.setBalance(account.getBalance());
		Pageable pageable = PageRequest.of(page, size, customizedSortCriteria.getSortCriteria());
		
		Page<Operation> pageOperations = operationRepository.findAll(toSpecification(customizedSearchCriteria), pageable);

		pageOperations.getContent().forEach(response::addHistory);
		
		return response;
	}

	/**
	 * Saves a new operation for a given account.
	 * 
	 * @param postRequest The operation post request
	 * @param account     The user account
	 * @return the new operation created
	 */
	private Operation createOperation(OperationPostRequest postRequest, Account account) {
		Operation operation = new Operation(postRequest.getAmount().doubleValue(), postRequest.getDescription(),
				account);
		return operationRepository.save(operation);
	}

	/**
	 * Updates a given account using the new amount.
	 * 
	 * @param amount  The operation amount
	 * @param account The user account
	 * @return the new account updated
	 */
	private Account updateBalanceAccount(Double amount, Account account) {
		Double newBalance = amount + account.getBalance();
		account.setBalance(newBalance);
		return accountRepository.save(account);
	}

}

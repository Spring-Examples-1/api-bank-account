/**
 * 
 */
package com.bank.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.bank.account.common.AbstractCommonTest;
import com.bank.account.controller.common.CustomizedSearchCriteria;
import com.bank.account.controller.common.CustomizedSearchCriteriaImpl;
import com.bank.account.controller.common.CustomizedSortCriteria;
import com.bank.account.controller.common.CustomizedSortCriteriaImpl;
import com.bank.account.controller.common.SearchCriteria;
import com.bank.account.exception.FunctionalException;
import com.bank.account.model.dto.OperationResponse;
import com.bank.account.model.dto.OperationsHistoryResponse;
import com.bank.account.model.entity.Account;
import com.bank.account.model.entity.Operation;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.OperationRepository;
import com.bank.account.service.core.CommonService;

/**
 * @author Rached
 *
 */
@SpringBootTest
class OperationServiceImplTest extends AbstractCommonTest {

	@Autowired
	private OperationService operationService;

	@MockBean
	private CommonService commonService;

	@MockBean
	private ClientService clientService;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private OperationRepository operationRepository;

	/**
	 * Test method for
	 * {@link com.bank.account.service.OperationServiceImpl#performDepositOrWithdrawal(com.bank.account.model.dto.OperationPostRequest, java.lang.String, java.lang.Long)}.
	 * 
	 * @throws FunctionalException
	 */
	@Test
	final void testPerformDepositOrWithdrawal() throws FunctionalException {
		// When
		when(operationRepository.save(any(Operation.class))).thenReturn(OPERATION_ENTITY_1);
		when(accountRepository.save(any(Account.class))).thenReturn(ACCOUNT_1_CLIENT_1);
		when(commonService.checksBeforeAllOperation(any(String.class), any(Long.class))).thenReturn(ACCOUNT_1_CLIENT_1);

		// Check method
		OperationResponse response = operationService.performDepositOrWithdrawal(OPERATION_REQUEST_OK, USER_NAME,
				ACCOUNT_NUMBER);

		// Assert
		OperationResponse expected = new OperationResponse(OPERATION_ENTITY_1);
		assertThat(response).isEqualTo(expected);

	}

	/**
	 * Test method for
	 * {@link com.bank.account.service.OperationServiceImpl#getAllOperationsHistory(java.lang.String, java.lang.Long, int, int)}.
	 * @throws FunctionalException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	final void testGetAllOperationsHistory() throws FunctionalException {
		// When
		when(commonService.checksBeforeAllOperation(any(String.class), any(Long.class))).thenReturn(ACCOUNT_1_CLIENT_1);
		when(operationRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(getPage());

		String fieldName = "operationId";
		CustomizedSortCriteria customizedSortCriteria = new CustomizedSortCriteriaImpl(fieldName);
		CustomizedSearchCriteria customizedSearchCriteria = new CustomizedSearchCriteriaImpl(new SearchCriteria(fieldName, ":", OPERATION_ID_1));
		
		// Check method
		OperationsHistoryResponse response = operationService.getAllOperationsHistory(USER_NAME, ACCOUNT_NUMBER, 0, 10, customizedSortCriteria, customizedSearchCriteria);
		
		// Assert
		OperationsHistoryResponse expected = getOperationsHistoryResponse();
		assertThat(response).isEqualTo(expected);
	}

	

}

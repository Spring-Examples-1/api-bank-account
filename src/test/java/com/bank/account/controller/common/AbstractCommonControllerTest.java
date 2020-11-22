/**
 * 
 */
package com.bank.account.controller.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.bank.account.exception.FunctionalException;

/**
 * @author Rached
 *
 */
class AbstractCommonControllerTest {

	/**
	 * Test method for
	 * {@link com.bank.account.controller.common.AbstractCommonController#toSearchCriteria(java.lang.String)}.
	 * @throws FunctionalException 
	 */
	@Test
	final void testToSearchCriteria() throws FunctionalException {
		AbstractCommonController absCls = Mockito.mock(AbstractCommonController.class, //
				Mockito.CALLS_REAL_METHODS);
		
		String key = "operationId";
		Mockito.when(absCls.getAuthorizedFields()).thenReturn(Collections.singletonList(key));
		
		String operation = ":";
		String value = "1";
		String searchBy = key + operation + value;
		CustomizedSearchCriteria criteria = absCls.toSearchCriteria(searchBy);

		for (SearchCriteria searchCriteria : criteria.getSearchCriterias()) {
			assertEquals(key, searchCriteria.getKey());
			assertEquals(operation, searchCriteria.getOperation());
			assertEquals(value, searchCriteria.getValue());
		}

	}

	/**
	 * Test method for
	 * {@link com.bank.account.controller.common.AbstractCommonController#toSortCriteria(java.lang.String)}.
	 * @throws FunctionalException 
	 */
	@Test
	final void testToSortCriteria() throws FunctionalException {
		AbstractCommonController absCls = Mockito.mock(AbstractCommonController.class, //
				Mockito.CALLS_REAL_METHODS);

		String fieldName = "operationId";
		Mockito.when(absCls.getAuthorizedFields()).thenReturn(Collections.singletonList(fieldName));
		
		String direction = "-";
		String sortBy = direction + fieldName;
		CustomizedSortCriteria criteria = absCls.toSortCriteria(sortBy);

		for (Order order : criteria.getSortCriterias()) {
			assertEquals(Direction.DESC, order.getDirection());
			assertEquals(fieldName, order.getProperty());
		}

		direction = "+";
		sortBy = direction + fieldName;
		criteria = absCls.toSortCriteria(sortBy);

		for (Order order : criteria.getSortCriterias()) {
			assertEquals(Direction.ASC, order.getDirection());
			assertEquals(fieldName, order.getProperty());
		}
	}

}

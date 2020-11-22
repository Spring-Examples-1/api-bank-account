/**
 * 
 */
package com.bank.account.controller.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * @author Rached
 *
 */
public class CustomizedSortCriteriaImpl implements CustomizedSortCriteria {
	/**
	 * Set of sort criteria
	 */
	private final List<Order> orders;

	public CustomizedSortCriteriaImpl() {
		super();
		orders = new ArrayList<>();
	}
	
	public CustomizedSortCriteriaImpl(String fieldName) {
		super();
		orders = new ArrayList<Order>(Arrays.asList(new Order(Sort.DEFAULT_DIRECTION, fieldName)));
	}

	@Override
	public void addSortCriteria(SortCriteria sortCriteria) {
		final String fieldName = sortCriteria.getFieldName();
		if ("-".equals(sortCriteria.getDirection())) {
			orders.add(new Order(Direction.DESC, fieldName));
		} else {
			orders.add(new Order(Sort.DEFAULT_DIRECTION, fieldName));
		}
	}

	@Override
	public List<Order> getSortCriterias() {
		return orders;
	}

	@Override
	public Sort getSortCriteria() {
		return Sort.by(orders);
	}

}

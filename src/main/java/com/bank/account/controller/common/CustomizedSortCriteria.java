/**
 * 
 */
package com.bank.account.controller.common;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * @author Rached
 *
 */
public interface CustomizedSortCriteria {

	void addSortCriteria(SortCriteria searchCriteria);
	
	List<Order> getSortCriterias();
	
	Sort getSortCriteria();

}

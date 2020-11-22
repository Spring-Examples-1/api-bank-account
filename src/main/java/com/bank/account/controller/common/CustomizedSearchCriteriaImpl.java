/**
 * 
 */
package com.bank.account.controller.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rached
 *
 */
public class CustomizedSearchCriteriaImpl implements CustomizedSearchCriteria {

	/**
	 * Set of search criteria
	 */
	private final Set<SearchCriteria> searchCriterias;
	
	public CustomizedSearchCriteriaImpl() {
		super();
		searchCriterias = new HashSet<>();
	}
	
	public CustomizedSearchCriteriaImpl(SearchCriteria searchCriteria) {
		super();
		searchCriterias = new HashSet<>(Arrays.asList(searchCriteria));
	}
	
	@Override
	public void addSearchCriteria(SearchCriteria searchCriteria) {
		searchCriterias.add(searchCriteria);
	}

	@Override
	public Set<SearchCriteria> getSearchCriterias() {
		return searchCriterias;
	}

}

package com.bank.account.controller.common;

import java.util.Set;

public interface CustomizedSearchCriteria {

	void addSearchCriteria(SearchCriteria searchCriteria);
	
	Set<SearchCriteria> getSearchCriterias();
}


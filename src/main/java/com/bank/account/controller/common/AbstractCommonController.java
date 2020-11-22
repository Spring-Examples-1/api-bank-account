/**
 * 
 */
package com.bank.account.controller.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bank.account.exception.FunctionalException;
import com.bank.account.util.ApiErrors;

/**
 * Class used to implements generic methods can used by all controller
 * 
 * @author Rached
 *
 */
public abstract class AbstractCommonController {

	/**
	 * Converts the searchBy parameter to a CustomizedSearchCriteria
	 * 
	 * @param searchBy The search by field name list in the format "(fieldName) +
	 *                 (:|<|>|) + (value) + (,)"
	 * @return {@link CustomizedSearchCriteria}
	 * @throws FunctionalException 
	 */
	protected CustomizedSearchCriteria toSearchCriteria(String searchBy) throws FunctionalException {
		CustomizedSearchCriteria spSearchCriteria = new CustomizedSearchCriteriaImpl();
		if (searchBy != null) {
			Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
			Matcher matcher = pattern.matcher(searchBy + ",");
			while (matcher.find()) {
				String fieldName = matcher.group(1);
				checkIsAuthorizedFields(fieldName);
				spSearchCriteria
						.addSearchCriteria(new SearchCriteria(fieldName, matcher.group(2), matcher.group(3)));
			}
		}
		return spSearchCriteria;
	}

	/**
	 * Converts the sortBy parameter to a CustomizedSortCriteria
	 * 
	 * @param sortBy The sort by field name list in the format "(+|-) + (fieldName)
	 *               +(,)"
	 * @return {@link CustomizedSortCriteria}
	 * @throws FunctionalException 
	 */
	protected CustomizedSortCriteria toSortCriteria(String sortBy) throws FunctionalException {
		CustomizedSortCriteria sortCriteria = new CustomizedSortCriteriaImpl();
		if (sortBy != null) {
			Pattern pattern = Pattern.compile("(\\+|-|)(\\w+?),");
			Matcher matcher = pattern.matcher(sortBy + ",");
			while (matcher.find()) {
				String fieldName = matcher.group(2);
				checkIsAuthorizedFields(fieldName);
				sortCriteria.addSortCriteria(new SortCriteria(matcher.group(1), fieldName));
			}
		}
		return sortCriteria;
	}
	
	
	/**
	 * @return the list of Authorized Fields in Search and Sort
	 */
	protected abstract List<String> getAuthorizedFields();
	
	
	/**
	 * Checks if given fields can be used in filter and sort parameters.
	 * 
	 * @param fieldName The field name used in search and sort.
	 * @throws FunctionalException
	 */
	private void checkIsAuthorizedFields(String fieldName) throws FunctionalException {
		if (!getAuthorizedFields().contains(fieldName)) {
			ApiErrors apiErrors = new ApiErrors();
			apiErrors.initErrorAndThrow(1004, fieldName);
		}
	}

}

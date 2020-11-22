/**
 * 
 */
package com.bank.account.service.core;

import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bank.account.controller.common.CustomizedSearchCriteria;
import com.bank.account.controller.common.SearchCriteria;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Rached
 *
 */
public abstract class AbstractSearchService<T> {

	public Specification<T> toSpecification(CustomizedSearchCriteria specificationSearchCriteria) {
	    return (root, criteriaQuery, criteriaBuilder) -> toPredicate(root, criteriaQuery, criteriaBuilder, specificationSearchCriteria);
	}
	
	private Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder, CustomizedSearchCriteria specificationSearchCriteria) {
		
    	Predicate predicate = criteriaBuilder.conjunction();
    	 
        UserSearchQueryCriteriaConsumer searchConsumer =  new UserSearchQueryCriteriaConsumer(predicate, criteriaBuilder, root);
        specificationSearchCriteria.getSearchCriterias().stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        criteriaQuery.where(predicate);
        
		return predicate;
	}
	
	
	@Data
	@AllArgsConstructor
	private class UserSearchQueryCriteriaConsumer implements Consumer<SearchCriteria>{
		 
	    private Predicate predicate;
	    private CriteriaBuilder builder;
	    private Root<?> r;

		@Override
		public void accept(SearchCriteria param) {
			if (param.getOperation().equalsIgnoreCase(">")) {
				predicate = builder.and(predicate,
						builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
			} else if (param.getOperation().equalsIgnoreCase("<")) {
				predicate = builder.and(predicate,
						builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
			} else if (param.getOperation().equalsIgnoreCase(":")) {
				if (r.get(param.getKey()).getJavaType() == String.class) {
					predicate = builder.and(predicate,
							builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
				} else {
					predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
				}
			}
		}

	}
}

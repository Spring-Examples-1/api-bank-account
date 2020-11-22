package com.bank.account.repository;
import org.springframework.stereotype.Repository;

import com.bank.account.model.entity.Operation;
import com.bank.account.repository.core.ApiRepositoryCustom;

/**
 * @author Rached
 *
 */
@Repository
public interface OperationRepository extends ApiRepositoryCustom <Operation, Long>{

	
}
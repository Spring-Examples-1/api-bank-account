package com.bank.account.repository;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account.model.entity.Account;
import com.bank.account.repository.core.ApiRepositoryCustom;

/**
 * @author Rached
 *
 */
@Repository
public interface AccountRepository  extends ApiRepositoryCustom <Account, Long> {

	Optional<Account> findByAccountNumber(Long accountNumber);


}
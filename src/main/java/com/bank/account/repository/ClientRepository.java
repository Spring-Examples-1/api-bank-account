package com.bank.account.repository;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bank.account.model.entity.Client;
import com.bank.account.repository.core.ApiRepositoryCustom;

/**
 * @author Rached
 *
 */
@Repository
public interface ClientRepository  extends ApiRepositoryCustom <Client, Long> {

	Optional<Client> findByUserName(String userName);

}
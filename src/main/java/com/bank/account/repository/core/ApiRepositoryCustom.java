package com.bank.account.repository.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ApiRepositoryCustom<T, I> extends JpaRepository<T, I>,  JpaSpecificationExecutor<T>{

}

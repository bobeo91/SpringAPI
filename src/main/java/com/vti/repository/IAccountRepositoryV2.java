package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vti.entity.Account;

@RepositoryRestResource(path = "accounts", collectionResourceRel = "accounts")
public interface IAccountRepositoryV2 extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

	Account findByUsername(String name);

//	@Modifying
//	@Query("From Account A where A.username = ?1")
//	List<Account> findAllByUsernameTrieu(String name);

	boolean existsByUsername(String name);

	boolean existsById(String name);

}
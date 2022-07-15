package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.Account;
import com.vti.filter.AccountFilterForm;
import com.vti.form.CreateAccountForm;
import com.vti.form.UpdateAccountForm;

public interface IAccountService extends UserDetailsService {

	void deleteAccountById(int id) throws Exception;

	void createAccount(CreateAccountForm createAccountForm) throws Exception;

	Account updateAccount(int id, UpdateAccountForm form) throws Exception;

	Account getAccountById(int id);

	Account getByname(String name);

	List<Account> getAllAccounts();

	boolean checkExitsByName(String name);

	boolean checkExitsById(int id);

	Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm filterForm);





}

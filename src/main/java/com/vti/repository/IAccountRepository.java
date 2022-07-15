package com.vti.repository;

import java.util.List;

import com.vti.entity.Account;

public interface IAccountRepository {

	List<Account> getAllAccounts(String name);

	Account getAccountById(int id);

	void deleteAccountById(int id);

	void updateAccount(Account acc);

}

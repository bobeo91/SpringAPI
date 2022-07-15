package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.filter.AccountFilterForm;
import com.vti.form.CreateAccountForm;
import com.vti.form.UpdateAccountForm;
import com.vti.repository.IAccountRepositoryV2;
import com.vti.repository.IDepartmentRepositoryV2;
import com.vti.specification.AccountSpecification;

@Service
@Primary
@Transactional
public class AccountServiceV2 implements IAccountService {
	@Autowired
	IAccountRepositoryV2 repositoryV2;

	@Autowired
	IDepartmentRepositoryV2 dpRepositoryV2;
	@Autowired
	ModelMapper mapper;

	@Transactional
	@Override
	public void deleteAccountById(int id) throws Exception {

		repositoryV2.deleteById(id);

	}

	@Transactional
	@Override
	public void createAccount(CreateAccountForm createAccountForm) throws Exception {
		TypeMap<CreateAccountForm, Account> typeMap = mapper.getTypeMap(CreateAccountForm.class, Account.class);
		if (typeMap == null) {
			mapper.addMappings(new PropertyMap<CreateAccountForm, Account>() {
				@Override
				protected void configure() {
					skip(destination.getId());
				}
			});
		}

		// Convert From to Account
		Account account = mapper.map(createAccountForm, Account.class);
		Department department = dpRepositoryV2.findById(createAccountForm.getDepartmentId()).get();
		account.setDepartment(department);

		repositoryV2.save(account);

	}

	@Transactional
	@Override
	public Account updateAccount(int id, UpdateAccountForm form) throws Exception {
		System.out.println(form);
		if (checkExitsById(id) == false) {
			throw new Exception("Account not exist, please try agian ");
		}
		Account acc = mapper.map(form, Account.class);
		Department department = dpRepositoryV2.findById(form.getDepartmentId()).get();
		acc.setDepartment(department);
		acc.setId(id);
		repositoryV2.save(acc);

		return acc;

	}

	@Override
	public Account getByname(String name) {
		if (checkExitsByName(name)) {
			return repositoryV2.findByUsername(name);
		}
		return null;
	}

	@Override
	public boolean checkExitsByName(String name) {
		return repositoryV2.existsByUsername(name);
	}

	@Override
	public boolean checkExitsById(int id) {
		return repositoryV2.existsById(id);
	}

	@Override
	public Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm filterForm) {
		Specification<Account> where = AccountSpecification.buildWhere(filterForm);
		return repositoryV2.findAll(where, pageable);
	}

	@Override
	public Account getAccountById(int id) {
		return repositoryV2.findById(id).get();

	}

	@Override
	public List<Account> getAllAccounts() {

		return repositoryV2.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = repositoryV2.findByUsername(username);

		if (account == null) {
			throw new UsernameNotFoundException(username);
		}

		return new User(account.getUsername(), account.getPassword(),
				AuthorityUtils.createAuthorityList(account.getRole().toString()));

	}

	
}

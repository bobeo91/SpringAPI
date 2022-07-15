package com.vti.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.annotation.AccountIdNotExists;
import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.filter.AccountFilterForm;
import com.vti.form.CreateAccountForm;
import com.vti.form.UpdateAccountForm;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "v1/api/accounts")
@CrossOrigin("*")
@Validated
public class AccountController {
	@Autowired
	private IAccountService service;
	@Autowired
	ModelMapper mapper;

	@GetMapping()
	public Page<AccountDTO> getAll(Pageable pageable, AccountFilterForm filterForm) {
		Page<Account> accountPages = service.getAllAccounts(pageable, filterForm);

		List<Account> listAccounts = accountPages.getContent();
		// convert entities --> dtos
		List<AccountDTO> dtos = mapper.map(listAccounts, new TypeToken<List<AccountDTO>>() {
		}.getType());

		Page<AccountDTO> dtoPages = new PageImpl<>(dtos, pageable, accountPages.getTotalElements());

		return dtoPages;
	}

	@GetMapping(value = "/{idAccount}")
	public AccountDTO getAccountById(@PathVariable(value = "idAccount") @AccountIdNotExists int id) {
		Account acc = service.getAccountById(id);
		// convert account to tto
		AccountDTO dto = mapper.map(acc, AccountDTO.class);
		return dto;
	}

	@GetMapping(value = "/name/{username}")
	public AccountDTO getByName(@PathVariable(value = "username") String username) {
		Account account = service.getByname(username);
		AccountDTO dto = mapper.map(account, AccountDTO.class);
		return dto;
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("ADMIN")
	public ResponseEntity<?> deleteById(@AccountIdNotExists @PathVariable(value = "id") int id) {
		try {
			service.deleteAccountById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Delete Success ");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PostMapping()
	public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountForm createAccountForm) {

		try {
			service.createAccount(createAccountForm);
			return ResponseEntity.status(HttpStatus.CREATED).body("Create Success ");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccount(@AccountIdNotExists @PathVariable(name = "id") int id,
			@RequestBody UpdateAccountForm form) {
		form.setId(id);
		try {
			if (service.updateAccount(id, form) != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Update Success ");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return null;
	}
}

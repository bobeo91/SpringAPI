package com.vti.controller;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.dto.LoginInfoDto;
import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "v1/api/login")
@CrossOrigin("*")	
public class AccountLoginController {

	@Autowired
	private IAccountService service;
	@Autowired
	ModelMapper mapper;
	

	@GetMapping()
	public ResponseEntity<?> login(Principal principal) {
		String userName = principal.getName();
		Account account = service.getByname(userName);
		LoginInfoDto dto = mapper.map(account, LoginInfoDto.class);
		
		return new ResponseEntity<> (dto,HttpStatus.OK);

	}
	
}

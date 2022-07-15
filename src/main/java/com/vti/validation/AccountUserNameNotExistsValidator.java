package com.vti.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.annotation.AccountUserNameNotExists;
import com.vti.service.IAccountService;

public class AccountUserNameNotExistsValidator implements ConstraintValidator<AccountUserNameNotExists, String>{
	@Autowired
	private IAccountService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(name)) {
			return true;
		}

		return !service.checkExitsByName(name);
	}

}

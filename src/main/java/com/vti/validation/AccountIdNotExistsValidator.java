package com.vti.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.annotation.AccountIdNotExists;
import com.vti.service.IAccountService;

public class AccountIdNotExistsValidator implements ConstraintValidator<AccountIdNotExists, Integer>{
	@Autowired
	private IAccountService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(id)) {
			return true;
		}

		return service.checkExitsById(id);
	}

}

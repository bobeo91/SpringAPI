package com.vti.exception;

public class AccountNotExistsException extends Exception{
	public AccountNotExistsException (String meString) {
		super(meString);
	}
}

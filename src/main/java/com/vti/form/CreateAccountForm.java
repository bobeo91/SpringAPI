package com.vti.form;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.vti.annotation.AccountUserNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountForm {
	@NotBlank(message = "{Account.createAccount.form.username.NotBlank}")
	@Length(min = 5, max = 50, message = "{Account.createAccount.form.username.Length}")
	@Email(message = "Email not valid, please enter @xxx.com !")
	private String email;
	
	@NotBlank(message = "Can not be null")
	@Length(min = 5 ,max = 50, message = "max = 50 char")
	@AccountUserNameNotExists
	private String username;
	
	@NotNull(message = "can not be null")
	@PositiveOrZero(message = "id must be greater than 0 ")
	private int departmentId;
	
	@NotBlank(message = "Can not be null")
	@Length(min = 5,max = 50, message = "max = 50 char")
	private String fullname;
	
	@NotBlank(message = "Can not be null")
	@Length(min = 5,max = 50, message = "max = 50 char")
	private String password;
	@Column(name = "Role")
	private String role;
	
	private Date createDate;

}

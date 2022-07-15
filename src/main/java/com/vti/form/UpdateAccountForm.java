package com.vti.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.vti.annotation.AccountIdNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateAccountForm {
	@NotNull(message = "can not be null")
	@PositiveOrZero(message = "id must be greater than 0 ")
	@AccountIdNotExists
	private int id;

	@NotBlank(message = "Can not be null")
	@Length(min = 5, max = 50, message = "max = 50 char")
	@Email
	private String email;

	@NotBlank(message = "Can not be null")
	@Length(min = 5, max = 50, message = "max = 50 char")
	private String username;

	@NotNull(message = "can not be null")
	@PositiveOrZero(message = "id must be greater than 0 ")
	private int departmentId;

	@NotBlank(message = "Can not be null")
	@Length(min = 5, max = 50, message = "max = 50 char")
	private String fullname;
	@NotBlank(message = "Can not be null")
	@Length(min = 5, max = 50, message = "max = 50 char")
	private String password;

	private Date createDate;

}

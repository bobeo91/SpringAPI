package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LoginInfoDto {

	private int id;
	private String username;
	private String fullname;
	private String email;
	private String password;
	private String departmentName;
	private int departmentId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
}

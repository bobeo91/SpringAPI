package com.vti.dto;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
	
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

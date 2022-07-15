package com.vti.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {
	private Integer minId;
	private Integer maxId;
	private Integer minDpId;
	private Integer maxDpId;
	private String departmentName;
	private String fullname;
	private String email;
	private String search;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;
}

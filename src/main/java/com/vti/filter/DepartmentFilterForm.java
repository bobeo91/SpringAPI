package com.vti.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentFilterForm {
	private Integer minId;
	private Integer maxId;
	private String departmentName;
	private String fullname;
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;
}

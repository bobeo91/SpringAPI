package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Department;
import com.vti.filter.DepartmentFilterForm;
import com.vti.form.CreateDepartmentForm;

public interface IDepartmentService {
	public List<Department> getAllDepartment();

	public Department getDepartmentById(int id);

	public List<Department> getDepartmentByName(String name);

	public void deleteDepartment(int id) throws Exception;

	public Department updateDepartment(int id, CreateDepartmentForm form) throws Exception;

	public Department createDepartment(CreateDepartmentForm form) throws Exception;

	boolean checkExitsByName(String name);

	public Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm filterForm);

}

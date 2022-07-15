package com.vti.repository;

import java.util.List;

import com.vti.entity.Department;
import com.vti.form.CreateDepartmentForm;

public interface IDepartmentRepository {

	List<Department> getAllDepartment();

	Department getDepartmentById(int id);

	Department getDepartmentByName(String name);

	void createDepartment(Department department);

	void updateDepartment(int id, String newName);

	void updateDepartment(Department department);

	void deleteDepartment(int id) throws Exception;

	

}

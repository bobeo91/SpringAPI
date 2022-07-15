package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.filter.DepartmentFilterForm;
import com.vti.form.CreateDepartmentForm;
import com.vti.repository.IAccountRepositoryV2;
import com.vti.repository.IDepartmentRepositoryV2;
import com.vti.specification.AccountSpecification;
import com.vti.specification.DepartmentSpecification;

@Service
@Primary
@Transactional
public class DepartmentServiceV2 implements IDepartmentService {
	@Autowired
	IDepartmentRepositoryV2 repositoryV2;
	@Autowired
	IAccountRepositoryV2 accountRepositoryV2;
	@Autowired
	ModelMapper mapper;

	@Override
	public List<Department> getAllDepartment() {

		return repositoryV2.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
	
		return repositoryV2.findById(id).get();
	}

	@Override
	public List<Department> getDepartmentByName(String name) {
		return repositoryV2.findByName(name);
	
	}
	@Transactional
	@Override
	public void deleteDepartment(int id) throws Exception {
		repositoryV2.deleteById(id);

	}
	@Transactional
	@Override
	public Department updateDepartment(int id, CreateDepartmentForm form) throws Exception {
		Department dp = repositoryV2.findById(id).get();
		if (dp == null) {
			throw new Exception(" Department not Available ");
		} else if (checkExitsByName(form.getName())) {
			throw new Exception(" Department name Available ");
		} else {
			dp.setName(form.getName());
			repositoryV2.save(dp);
			return dp;
		}
		
	}
	@Transactional
	@Override
	public Department createDepartment(CreateDepartmentForm form) throws Exception {
	
		if (checkExitsByName(form.getName())) {
			throw new Exception(" Department name existed ");
		}
		Department department1 = mapper.map(form, Department.class);
		Department department2= repositoryV2.save(department1);
		List<Account> accounts = department1.getAccounts();
		for (Account account : accounts) {
			account.setDepartment(department2);
		}
		accountRepositoryV2.saveAll(accounts);
		
		return department2;

	}

	@Override
	public boolean checkExitsByName(String name) {
		return repositoryV2.existsByName(name);
	}

	@Override
	public Page<Department> getAllDepartments(Pageable pageable, DepartmentFilterForm filterForm) {
		Specification<Department> where = DepartmentSpecification.buildWhere(filterForm);
		return repositoryV2.findAll(where,pageable);

	}

}

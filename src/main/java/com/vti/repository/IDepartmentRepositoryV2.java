package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Department;

public interface IDepartmentRepositoryV2 extends JpaRepository<Department, Integer> ,JpaSpecificationExecutor<Department>{

	boolean existsByName(String name);

	List<Department> findByName(String name);

}

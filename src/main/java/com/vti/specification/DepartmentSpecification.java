package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Department;
import com.vti.filter.DepartmentFilterForm;

public class DepartmentSpecification {
	public static Specification<Department> buildWhere(DepartmentFilterForm filterForm) {

		Specification<Department> where1 = new CustomDepartmentSpecification(KeySp.KEY_EMAIL_LIKE,
				filterForm.getEmail());
		Specification<Department> where2 = new CustomDepartmentSpecification(KeySp.KEY_FULLNAME_LIKE,
				filterForm.getFullname());
		Specification<Department> where3 = new CustomDepartmentSpecification(KeySp.KEY_MAX_ID, filterForm.getMaxId());
		Specification<Department> where4 = new CustomDepartmentSpecification(KeySp.KEY_MIN_ID, filterForm.getMinId());
		Specification<Department> where5 = new CustomDepartmentSpecification(KeySp.KEY_DEPARTMENTNAME_LIKE,
				filterForm.getDepartmentName());
		Specification<Department> where6 = new CustomDepartmentSpecification(KeySp.KEY_CREATE_DATE,
				filterForm.getCdate());

		return Specification.where(where1).and(where2).and(where3).and(where4).and(where5).and(where6);

	}

	public interface KeySp {
	
		public static final String KEY_EMAIL_LIKE = "KEY_EMAIL_LIKE";
		public static final String KEY_FULLNAME_LIKE = "KEY_FULLNAME_LIKE";
		public static final String KEY_DEPARTMENTNAME_LIKE = "KEY_DEPARTMENTNAME_LIKE";
		public static final String KEY_MIN_ID = "KEY_MIN_ID";
		public static final String KEY_MAX_ID = "KEY_MAX_ID";
		public static final String KEY_CREATE_DATE = "KEY_CREATE_DATE";
	}

}

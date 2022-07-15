package com.vti.specification;


import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.specification.AccountSpecification.KeySp;

@SuppressWarnings("serial")
public class CustomDepartmentSpecification implements Specification<Department>{
	
	String key;
	
	Object value;

	public CustomDepartmentSpecification(String key, Object value) {
	
		this.key = key;
		this.value = value;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (value ==null) {
			return null;
		}
		Join join =root.join("accounts",JoinType.LEFT);
		switch (key) {
	
		case KeySp.KEY_EMAIL_LIKE:
			return criteriaBuilder.like(join.get("email"), "%"+value+"%");
		case KeySp.KEY_FULLNAME_LIKE:
			return criteriaBuilder.like(join.get("fullname"),"%"+ value+"%");
		case KeySp.KEY_DEPARTMENTNAME_LIKE:
			return criteriaBuilder.like(join.get("departmentName"),"%"+ value+"%");
		case KeySp.KEY_MAX_ID:
			//id <= max
			return criteriaBuilder.lessThanOrEqualTo(join.get("id"), value.toString());
		case KeySp.KEY_MIN_ID:
			//id >= min
			return criteriaBuilder.greaterThanOrEqualTo(join.get("id"),value.toString());

		case KeySp.KEY_CREATE_DATE:
			//id >= min
			return criteriaBuilder.equal(join.get("createDate").as(java.sql.Date.class),(Date) value);
		}

		
		return null;
	}

}

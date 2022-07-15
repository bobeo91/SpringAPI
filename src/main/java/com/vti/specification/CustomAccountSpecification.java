package com.vti.specification;


import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Account;
import com.vti.specification.AccountSpecification.KeySp;

@SuppressWarnings("serial")
public class CustomAccountSpecification implements Specification<Account>{
	
	String key;
	
	Object value;

	public CustomAccountSpecification(String key, Object value) {
	
		this.key = key;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (value ==null) {
			return null;
		}
		switch (key) {
		case KeySp.KEY_SEARCH:
			return criteriaBuilder.like(root.get("username"),"%"+ value+"%");
		case KeySp.KEY_EMAIL_LIKE:
			return criteriaBuilder.like(root.get("email"), "%"+value+"%");
		case KeySp.KEY_FULLNAME_LIKE:
			return criteriaBuilder.like(root.get("fullname"),"%"+ value+"%");
		case KeySp.KEY_DEPARTMENTNAME_LIKE:
			return criteriaBuilder.like(root.get("department").get("name"),"%"+ value+"%");
		case KeySp.KEY_MAX_ID:
			//id <= max
			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
		case KeySp.KEY_MIN_ID:
			//id >= min
			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"),value.toString());
		case KeySp.KEY_MAX_DP_ID:
			//id <= max
			return criteriaBuilder.lessThanOrEqualTo(root.get("department").get("id"), value.toString());
		case KeySp.KEY_MIN_DP_ID:
			//id >= min
			return criteriaBuilder.greaterThanOrEqualTo(root.get("department").get("id"),value.toString());
		case KeySp.KEY_CREATE_DATE:
			//id >= min
			return criteriaBuilder.equal(root.get("createDate").as(java.sql.Date.class),(Date) value);
		}

		
		return null;
	}

}

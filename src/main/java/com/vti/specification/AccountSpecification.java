package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;

import com.vti.entity.Account;
import com.vti.filter.AccountFilterForm;

public class AccountSpecification {

	public static Specification<Account> buildWhere( AccountFilterForm filterForm) {

		Specification<Account> where1 = new CustomAccountSpecification(KeySp.KEY_SEARCH,filterForm.getSearch());
		Specification<Account> where2 = new CustomAccountSpecification(KeySp.KEY_EMAIL_LIKE,filterForm.getEmail());
		Specification<Account> where3 = new CustomAccountSpecification(KeySp.KEY_FULLNAME_LIKE,filterForm.getFullname());
		Specification<Account> where4 = new CustomAccountSpecification(KeySp.KEY_MAX_ID,filterForm.getMaxId());
		Specification<Account> where5 = new CustomAccountSpecification(KeySp.KEY_MIN_ID,filterForm.getMinId());
		Specification<Account> where6 = new CustomAccountSpecification(KeySp.KEY_DEPARTMENTNAME_LIKE,filterForm.getDepartmentName());
		Specification<Account> where7 = new CustomAccountSpecification(KeySp.KEY_MAX_DP_ID,filterForm.getMaxDpId());
		Specification<Account> where8 = new CustomAccountSpecification(KeySp.KEY_MIN_DP_ID,filterForm.getMinDpId());
		Specification<Account> where9 = new CustomAccountSpecification(KeySp.KEY_CREATE_DATE,filterForm.getCdate());
		
		return Specification.where(where1).and(where2).and(where3).and(where4).and(where5).and(where6).and(where7).and(where8).and(where9);
	
	}
	public interface KeySp{
		public static final String KEY_SEARCH = "KEY_SEARCH";
		public static final String KEY_EMAIL_LIKE = "KEY_EMAIL_LIKE";
		public static final String KEY_FULLNAME_LIKE = "KEY_FULLNAME_LIKE";
		public static final String KEY_DEPARTMENTNAME_LIKE = "KEY_DEPARTMENTNAME_LIKE";
		public static final String KEY_MIN_ID = "KEY_MIN_ID";
		public static final String KEY_MAX_ID = "KEY_MAX_ID";
		public static final String KEY_MIN_DP_ID = "KEY_MIN_DP_ID";
		public static final String KEY_MAX_DP_ID = "KEY_MAX_DP_ID";
		public static final String KEY_CREATE_DATE = "KEY_CREATE_DATE";
	}
}

//@SuppressWarnings("serial")
//@RequiredArgsConstructor
//class CustomSpecification implements Specification<Account> {
//
//	@NonNull
//	private String field;
//	@NonNull
//	private Object value;
//
//	@Override
//	public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//
//		if (field.equalsIgnoreCase("username")) {
//			return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
//		}
//
//		if (field.equalsIgnoreCase("minId")) {
//			return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
//		}
//
//		if (field.equalsIgnoreCase("maxId")) {
//			return criteriaBuilder.lessThanOrEqualTo(root.get("id"), value.toString());
//		}
//
//		return null;
//	}
//
//}

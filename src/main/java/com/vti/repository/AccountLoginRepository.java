package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.AccountLogin;
import com.vti.entity.TypeQuestion;

import utils.HibernateUtil;

public class AccountLoginRepository {
	public List<AccountLogin> getAllAccountLogin(int page, int size) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM AccountLogin";
			Query<AccountLogin> query = session.createQuery(hqlQuery);
			int offset = (page-1)*size;
			int limit = size;
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<AccountLogin> list = query.list();

			return list;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public void createAccountLogin(AccountLogin accountLogin ) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.save(accountLogin);
			session.getTransaction().commit();
			System.out.println("--------------Tao thanh cong----------------");
		
		} finally {
			if (session != null) {
				session.close();
			}// TODO: handle finally clause
		}
	}
}

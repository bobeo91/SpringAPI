package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.CategoryQuestion;
import com.vti.entity.GroupAccount;

import utils.HibernateUtil;

public class GroupAccountRepository {
	public void createGroupAccount(GroupAccount grac) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.save(grac);
			session.getTransaction().commit();
			System.out.println("--------------Tao thanh cong----------------");
		} finally {
			if (session != null) {
				session.close();
			}
			// TODO: handle finally clause
		}
	}
	
	public List<GroupAccount> getAllGroupAccount(int page, int size) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM GroupAccount";
			
			Query<GroupAccount> query = session.createQuery(hqlQuery);
			int offset = (page-1)*size;
			int limit = size;
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<GroupAccount> list = query.list();

			return list;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}

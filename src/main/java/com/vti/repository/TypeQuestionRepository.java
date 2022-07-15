package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.CategoryQuestion;
import com.vti.entity.TypeQuestion;

import utils.HibernateUtil;

public class TypeQuestionRepository {
	public List<TypeQuestion> getAllTypeQuestion() {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM TypeQuestion";
			Query<TypeQuestion> query = session.createQuery(hqlQuery);
			List<TypeQuestion> list = query.list();

			return list;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public void createTypeQuestion(TypeQuestion typeQuestion ) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.save(typeQuestion);
			session.getTransaction().commit();
			System.out.println("--------------Tao thanh cong----------------");
		
		} finally {
			if (session != null) {
				session.close();
			}// TODO: handle finally clause
		}
	}
}

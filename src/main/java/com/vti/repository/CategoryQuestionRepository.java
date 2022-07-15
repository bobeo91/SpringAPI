package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Position;
import com.vti.entity.CategoryQuestion;

import utils.HibernateUtil;

public class CategoryQuestionRepository {
	public List<CategoryQuestion> getAllCategoryQuestions() {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM CategoryQuestion";
			Query<CategoryQuestion> query = session.createQuery(hqlQuery);
			List<CategoryQuestion> list = query.list();

			return list;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	public void createCategoryQuestion(CategoryQuestion categoryQuestion ) {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.save(categoryQuestion);
			session.getTransaction().commit();
			System.out.println("--------------Tao thanh cong----------------");
		
		} finally {
			if (session != null) {
				session.close();
			}// TODO: handle finally clause
		}
	}
}

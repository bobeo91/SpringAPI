package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Group;
import com.vti.entity.Position;

import utils.HibernateUtil;

public class PositionRepository {

	public List<Position> getAllPositions() {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM Position";
			Query<Position> query = session.createQuery(hqlQuery);
			List<Position> listPosition = query.list();

			return listPosition;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}

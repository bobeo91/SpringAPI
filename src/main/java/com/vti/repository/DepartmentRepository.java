package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.CreateDepartmentForm;

import utils.HibernateUtil;

@Repository
public class DepartmentRepository implements IDepartmentRepository {
	
	@Override
	public List<Department> getAllDepartment( ) {
		Session session = null;
		try {
			// de truy van csdl can tao 1 session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "From Department";
			
			Query<Department> query = session.createQuery(hqlQuery);
			List<Department> list = query.list();
			return list;
		} finally {
			if (session != null) {
				session.close();
			}

		}
	}

	@Override
	public Department getDepartmentById(int id) {
		Session session = null;
		try {
			// get sesion
			session = HibernateUtil.getFactory().openSession();
			// create hql query
			String hqlQuery = "From Department D WHERE D.id= " + id;
			Query<Department> query = session.createQuery(hqlQuery);
			// get result
			Department department = query.getSingleResult();
			return department;
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	@Override
	public void createDepartment(Department department) {

		Session session = null;

		try {
			// get session
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			// create
			session.save(department);
			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void updateDepartment(Department department) {
		Session session = null;

		try {

			// get session
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			// create
			session.update(department);
			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void updateDepartment(int id, String newName) {

		Session session = null;

		try {

			// get session
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			// create
			Department department = session.get(Department.class, id);
			department.setName(newName);

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Department getDepartmentByName(String name) {
		Session session = null;
		try {
			// get sesion
			session = HibernateUtil.getFactory().openSession();
			// create hql query
			String hqlQuery = "From Department D WHERE D.name = :nameParameter";
			Query<Department> query = session.createQuery(hqlQuery);
			query.setParameter("nameParameter", name);
			// get result
			Department department = query.getSingleResult();
			return department;
		} finally {
			if (session != null) {
				session.close();
			}

		}
	}

	@Override
	public void deleteDepartment(int id) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			// create hql query
			session.beginTransaction();
			String hqlQuery = "From Department D WHERE D.id= " + id;
			Query<Department> query = session.createQuery(hqlQuery);
			// get result
			Department department = query.getSingleResult();
			if (department==null) {
				throw new Exception("This Department is not available");
			}
			session.delete(department);
			session.getTransaction().commit();
		} finally {
		
			if (session != null) {
				session.close();
			}

		}
	}

	

	

	

}

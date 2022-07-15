package com.vti.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Group;

import utils.HibernateUtil;

public class GroupRepository {

	public Group createGroup(String name, String sdate) {
		Session session = null;
		Group group = new Group(name, sdate);
		try {
			// de truy van csdl can tao 1 session
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.save(group);
			session.getTransaction().commit();
			System.out.println("Tao thanh cong");
			return group;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<Group> getAllGroups() {
		Session session = null;
		try {
			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM Group";
			Query<Group> query = session.createQuery(hqlQuery);
			List<Group> listGroups = query.list();

			return listGroups;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Group getGroupByID(int id) {
		Session session = null;
		try {

			session = HibernateUtil.getFactory().openSession();
			Group group = session.get(Group.class, id);

			return group;

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Group getGroupByName(String name) {
		Session session = null;
		try {

			session = HibernateUtil.getFactory().openSession();
			String hqlQuery = "FROM Group G WHERE G.GroupName=?1";
			Query<Group> query = session.createQuery(hqlQuery);
			query.setParameter(1, name);
			Group group = query.getSingleResult();

			return group;

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void updateGroup(int newID, String newName, LocalDateTime newDate) {

		Session session = null;

		try {
			// de truy van csdl can tao 1 session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "UPDATE Group G SET G.GroupID= ?1, G.GroupName=?2, G.CreateDate=?3";
			Query<Group> query = session.createQuery(hqlQuery);
			query.setParameter(1, newID);
			query.setParameter(2, newName);
			query.setParameter(3, newDate);

			session.beginTransaction();
			int count = query.executeUpdate();
			if (count > 0) {
				System.out.println("Update thanh cong");
			} else {
				System.out.println("Update thất bại => không tìm thấy đối tượng cần update");
			}
			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}

		}
	}

	public void deleteAccountByIdCach1(int id) {
		Session session = null;
		try {

			// de truy van csdl can tao 1 session
			session = HibernateUtil.getFactory().openSession();
			Group group = session.get(Group.class, id);

			if (group == null) {
				System.out.println("delete that bai");
				return;
			}
			session.beginTransaction();
			session.delete(group);
			session.getTransaction().commit();
			System.out.println("delete thanh cong");

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public void deleteAccountByIdCach2(int id) {
		Session session = null;
		try {
			// de truy van csdl can tao 1 session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "DELETE FROM Group G WHERE G.GroupID = ?1";
			Query<Group> query = session.createQuery(hqlQuery);
			query.setParameter(1, id);
			session.beginTransaction();
			int count = query.executeUpdate();
			session.getTransaction().commit();
			if (count > 0) {
				System.out.println("delete thanh cong");
			} else {
				System.out.println("delete thất bại => không tìm thấy id cần xóa");
			}

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public boolean isGroupExistByID(int id) {
		// get account
		Group group = getGroupByID(id);
		// return result
		if (group == null) {
			System.out.println("Group khong ton tai");
			return false;
		}
		return true;
	}

	public boolean isGroupExistByName(String name) {
		// get account
		Group group = getGroupByName(name);
		// return result
		if (group == null) {
			System.out.println("Group khong ton tai");
			return false;
		}
		return true;
	}
}

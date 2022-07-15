package com.vti.repository;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vti.entity.Account;

import utils.HibernateUtil;

@Repository
public class AccountRepository implements IAccountRepository {
	@Override
	public List<Account> getAllAccounts(String name) {
		Session session = null;
		try {
			// create session
			session = HibernateUtil.getFactory().openSession();
			// create Hibernate query
			String hqlQuery = "From Account";
			if (name != null) {
				hqlQuery = String.format("From Account A where A.username='%s'", name);
			}
			Query<Account> query = session.createQuery(hqlQuery);
			List<Account> listAccounts = query.list();
			return listAccounts;
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}
//
//	@Override
//	public void createAccount(Account account) {
//		Session session = null;
//
//		try {
//			// create session
//			session = HibernateUtil.getFactory().openSession();
//			session.beginTransaction();
//			session.save(account);
//			session.getTransaction().commit();
//			System.out.println("Tao thanh cong");
//
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//
//	}

	@Override
	public void deleteAccountById(int id) {
		Session session = null;
		try {
			// create session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "FROM Account A WHERE A.id= " + id;
			Query<Account> query = session.createQuery(hqlQuery);
			Account account = query.getSingleResult();
			// c2 Account account = session.get(Account.class, id)
			if (account == null) {
				System.out.println("delete that bai");
				return;
			}
			session.beginTransaction();
			session.delete(account);
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
			// create session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "DELETE FROM Account A WHERE A.id = " + id;
			Query<Account> query = session.createQuery(hqlQuery);
			session.beginTransaction();
			int count = query.executeUpdate();
			session.getTransaction().commit();
			if (count > 0) {
				System.out.println("delete thanh cong");
			} else {
				System.out.println("delete thất bại => không tìm thấy id cần xóa");
			}
//			c2  account = session.get(Account.class, id)

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public void updateAccountById(int id, String usernameNew, String emailNew) {
		Session session = null;
		try {
			// create session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "FROM Account A WHERE A.id= " + id;
			Query<Account> query = session.createQuery(hqlQuery);
			Account account = query.getSingleResult();

//			c2  account = session.get(Account.class, id)
			session.beginTransaction();
			account.setUsername(usernameNew);
			account.setEmail(emailNew);

			session.getTransaction().commit();
			System.out.println("update thanh cong");

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public void updateAccount(int idnew, String usernameNew, String emailNew) {
		Session session = null;
		try {
			// create session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "UPDATE Account A SET A.id= ?1, A.username=?2, A.email=?3";
			Query<Account> query = session.createQuery(hqlQuery);
			query.setParameter(1, idnew);
			query.setParameter(2, usernameNew);
			query.setParameter(3, emailNew);

//			c2  account = session.get(Account.class, id)
			session.beginTransaction();
			int count = query.executeUpdate();
			if (count > 0) {
				System.out.println("Update thanh cong");
			} else {
				System.out.println("Update thất bại => không tìm thấy đối tượng");
			}
			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public boolean isAcountExistByID(int id) {
		// get account
		Account account = getAccountById(id);
		// return result
		if (account == null) {
			System.out.println("Account khong ton tai");
			return false;
		}
		return true;
	}

	@Override
	public Account getAccountById(int id) {
		Session session = null;
		try {
			// create session
			session = HibernateUtil.getFactory().openSession();
			// tao cau lenh Hibernate query
			String hqlQuery = "FROM Account A WHERE A.id= " + id;
			Query<Account> query = session.createQuery(hqlQuery);
			Account account = query.getSingleResult();
			// cach2
			/*
			 * String hqlQuery = "FROM Account A WHERE A.id= :idAccount"; Query<Account>
			 * query = session.createQuery(hqlQuery); query.setParameter("idAccount",id);
			 */

//			c3  account = session.get(Account.class, id)

			return account;
		} finally {
			if (session != null) {
				session.close();
			}
			// TODO: handle finally clause
		}
	}

	@Override
	public void updateAccount(Account acc) {
		Session session = null;
		try {
			// create session
			session = HibernateUtil.getFactory().openSession();
			session.beginTransaction();
			session.update(acc);
			session.getTransaction().commit();
			

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

}

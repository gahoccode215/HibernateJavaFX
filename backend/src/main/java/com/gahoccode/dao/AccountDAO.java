package com.gahoccode.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gahoccode.pojo.Account;
import com.gahoccode.pojo.Student;

public class AccountDAO {
	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

	public AccountDAO(String persistanceName) {
		cf = new Configuration();
		cf = cf.configure(persistanceName);
		sessionFactory = cf.buildSessionFactory();
	}

	public Account findByUserName(String userName) {
		Session session = sessionFactory.openSession();
		try {
			return (Account) session.get(Account.class, userName);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void save(Account account) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(account);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}
}

package com.gahoccode.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	
}

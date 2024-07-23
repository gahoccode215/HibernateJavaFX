package com.gahoccode.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.gahoccode.pojo.Student;

public class StudentDAO {
	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

	public StudentDAO(String persistanceName) {
		cf = new Configuration();
		cf = cf.configure(persistanceName);
		sessionFactory = cf.buildSessionFactory();
	}

	public void save(Student student) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(student);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
	}

	public void delete(int studentID) {
		Session session = sessionFactory.openSession();
		Transaction t = session.getTransaction();
		try {
			t.begin();
			Student student = (Student) session.get(Student.class, studentID);
			session.delete(student);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public Student findById(int studentID) {
		Session session = sessionFactory.openSession();
		try {
			return (Student) session.get(Student.class, studentID);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void update(Student student) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(student);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	
	public List<Student> getStudents(){
		List<Student> students = new ArrayList<Student>();
		Session session = sessionFactory.openSession();
		try {
			students = session.createQuery("FROM Student").getResultList();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return students;
	}

}

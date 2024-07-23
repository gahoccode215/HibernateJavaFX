package com.gahoccode.service;

import java.util.List;

import com.gahoccode.pojo.Student;
import com.gahoccode.repository.IStudentRepository;
import com.gahoccode.repository.StudentRepository;

public class StudentService implements IStudentService{
	private IStudentRepository iStudentRepo = null;
	
	public StudentService(String fileName) {
		iStudentRepo = new StudentRepository(fileName);
	}
			
	@Override
	public List<Student> findAll() {
		return iStudentRepo.findAll();
	}

	@Override
	public void save(Student student) {
		iStudentRepo.save(student);
	}

	@Override
	public void delete(int studentID) {
		iStudentRepo.delete(studentID);
	}

	@Override
	public Student findById(int studentID) {
		return iStudentRepo.findById(studentID);
	}

	@Override
	public void update(Student student) {
		iStudentRepo.update(student);
	}
}

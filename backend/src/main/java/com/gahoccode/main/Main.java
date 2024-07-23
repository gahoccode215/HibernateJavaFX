package com.gahoccode.main;

import com.gahoccode.pojo.Book;
import com.gahoccode.pojo.Student;
import com.gahoccode.service.IStudentService;
import com.gahoccode.service.StudentService;

public class Main {
	public static void main(String[] args) {
		String fileName = "hibernate.cfg.xml";
		IStudentService studentService = new StudentService(fileName);
		Student student = new Student("Minh", "Do", 10);
		Book book = new Book("Java", "James", "113");
		student.getBooks().add(book);
		studentService.save(student);
	}
}

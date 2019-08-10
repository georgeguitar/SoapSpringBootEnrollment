package com.dirceu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dirceu.entity.Student;
import com.dirceu.repository.StudentRepository;
@Service
public class StudentService implements IStudentService {
	@Autowired
	private StudentRepository StudentRepository;
	
	@Override
	public Student getStudentById(long studentId) {
		Student obj = StudentRepository.findByStudentId(studentId);
		return obj;
	}	
	@Override
	public List<Student> getAllStudents(){
		List<Student> list = new ArrayList<>();
		StudentRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addStudent(Student student){
	   List<Student> list = StudentRepository.findByNameAndSurname(student.getName(), student.getSurname());
       if (list.size() > 0) {
    	   return false;
       } else {
    	   student = StudentRepository.save(student);
    	   return true;
       }
	}
	@Override
	public void updateStudent(Student student) {
		StudentRepository.save(student);
	}
	@Override
	public void deleteStudent(Student student) {
		StudentRepository.delete(student);
	}
}

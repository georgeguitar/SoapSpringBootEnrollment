package com.dirceu.service;

import java.util.List;

import com.dirceu.entity.Student;

public interface IStudentService {
     List<Student> getAllStudents();
     Student getStudentById(long studentId);
     boolean addStudent(Student student);
     void updateStudent(Student student);
     void deleteStudent(Student student);
}

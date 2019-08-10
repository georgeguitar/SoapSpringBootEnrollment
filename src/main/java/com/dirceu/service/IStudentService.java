package com.dirceu.service;

import java.util.List;

import com.dirceu.entity.Student;

public interface IStudentService {
     List<Student> getAllStudents();
     Student getStudentById(long StudentId);
     boolean addStudent(Student Student);
     void updateStudent(Student Student);
     void deleteStudent(Student Student);
}

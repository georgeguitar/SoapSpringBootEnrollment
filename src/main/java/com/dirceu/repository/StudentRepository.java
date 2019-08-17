package com.dirceu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dirceu.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long>  {
	Student findByStudentId(long studentId);
    List<Student> findByNameAndSurname(String name, String surname);
}

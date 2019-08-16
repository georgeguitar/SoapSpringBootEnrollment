package com.dirceu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dirceu.entity.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long>  {
	Enrollment findByEnrollmentId(long EnrollmentId);
    List<Enrollment> findByStudentIdAndProgramId(String studentId, String programId);
}

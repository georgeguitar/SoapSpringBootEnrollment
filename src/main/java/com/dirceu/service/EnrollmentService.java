package com.dirceu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dirceu.entity.Enrollment;
import com.dirceu.repository.EnrollmentRepository;
@Service
public class EnrollmentService implements IEnrollmentService {
	@Autowired
	private EnrollmentRepository EnrollmentRepository;
	
	@Override
	public Enrollment getEnrollmentById(long enrollmentId) {
		Enrollment obj = EnrollmentRepository.findByEnrollmentId(enrollmentId);
		return obj;
	}	
	@Override
	public List<Enrollment> getAllEnrollments(){
		List<Enrollment> list = new ArrayList<>();
		EnrollmentRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addEnrollment(Enrollment enrollment){
	   List<Enrollment> list = EnrollmentRepository.findByStudentIdAndProgramId(enrollment.getStudentId(), enrollment.getProgramId());
       if (list.size() > 0) {
    	   return false;
       } else {
    	   enrollment = EnrollmentRepository.save(enrollment);
    	   return true;
       }
	}
	@Override
	public void updateEnrollment(Enrollment enrollment) {
		EnrollmentRepository.save(enrollment);
	}
	@Override
	public void deleteEnrollment(Enrollment enrollment) {
		EnrollmentRepository.delete(enrollment);
	}
}

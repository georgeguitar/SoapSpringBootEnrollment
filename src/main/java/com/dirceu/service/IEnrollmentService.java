package com.dirceu.service;

import java.util.List;

import com.dirceu.entity.Enrollment;

public interface IEnrollmentService {
     List<Enrollment> getAllEnrollments();
     Enrollment getEnrollmentById(long enrollmentId);
     boolean addEnrollment(Enrollment enrollment);
     void updateEnrollment(Enrollment enrollment);
     void deleteEnrollment(Enrollment enrollment);
}

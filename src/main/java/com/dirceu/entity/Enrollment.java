package com.dirceu.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="enrollment")
public class Enrollment implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="id")
    private long enrollmentId;  
	@Column(name="year")
    private String year;
	@Column(name="period")	
	private String period;
	@Column(name="level")	
	private String level;
	@Column(name="date_enrollment")	
	private String dateEnrollment;
	@Column(name="student_id")	
	private String studentId;	
	@Column(name="program_id")	
	private String programId;

	public long getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDateEnrollment() {
		return dateEnrollment;
	}
	public void setDateEnrollment(String dateEnrollment) {
		this.dateEnrollment = dateEnrollment;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
} 
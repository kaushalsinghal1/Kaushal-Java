package com.ace.training.usecase.sms;

public interface IEnrollCourse {
	Student enrollStudent(Student student) throws EnrollmentException;

}

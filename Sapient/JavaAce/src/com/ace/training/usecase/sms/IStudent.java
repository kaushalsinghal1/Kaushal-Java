package com.ace.training.usecase.sms;

public interface IStudent {

	void enrollCourse(Student student, Course cource, String paymentType) throws EnrollmentException;

}

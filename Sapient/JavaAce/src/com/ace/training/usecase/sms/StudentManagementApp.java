package com.ace.training.usecase.sms;

import com.ace.training.usecase.sms.notification.NotificationService;

public class StudentManagementApp {

	public static void main(String[] args) {
		StudentHandler studentHandler = new StudentHandler(
				new NotificationService(), new CourseValidator());
		try {
			// Dummy
			Student student = new Student();
			student.setName("kaushal");
			studentHandler.enrollStudent(student);
			studentHandler.enrollCourse(student, CourseHandler.getInstanse()
					.getCourse(1), "creditcard");

		} catch (EnrollmentException e) {
			e.printStackTrace();
		}
	}
}

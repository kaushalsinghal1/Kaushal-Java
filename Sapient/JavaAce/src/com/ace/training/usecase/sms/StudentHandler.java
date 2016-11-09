package com.ace.training.usecase.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ace.training.usecase.sms.notification.NotificationService;
import com.ace.training.usecase.sms.payment.IPayment;
import com.ace.training.usecase.sms.payment.PaymentStatus;

public class StudentHandler implements IStudent, IEnrollCourse {
	private final IPayment payment;
	private final NotificationService notificationService;
	private final CourseValidator courseValidator;
	private Map<Integer, Student> studentMap;
	private Map<Integer, List<StudentSubscribedCourse>> studentCourseMap;

	public StudentHandler(IPayment payment, NotificationService notificationService, CourseValidator courseValidator) {
		this.payment = payment;
		this.notificationService = notificationService;
		this.courseValidator = courseValidator;
		studentMap = new HashMap<>();
		studentCourseMap = new HashMap<>();
	}

	@Override
	public void enrollCourse(Student student, Course course) throws EnrollmentException {
		if (!courseValidator.isElegible(student, course)) {
			throw new EnrollmentException("Student is not eligible to enroll this course: " + course.getName());
		}
		// Payment
		PaymentStatus paymentStatus = payment.doPayment(course.getCost());
		if (!paymentStatus.isSuccess()) {
			notificationService.notifyUser(student);
		} else {
			// Payment Success
			// Enroll the course
			StudentSubscribedCourse subscribedCourse = new StudentSubscribedCourse(student, course);
			subscribedCourse.setPaymentStatus(paymentStatus);
			addSubscribedCourse(subscribedCourse);
			notificationService.notifyUser(student);

		}

	}

	private void addSubscribedCourse(StudentSubscribedCourse cource) {
		synchronized (studentCourseMap) {
			List<StudentSubscribedCourse> list = studentCourseMap.get(cource.getStudent().getStudentId());
			if (list == null) {
				list = new ArrayList<>();
				studentCourseMap.put(cource.getStudent().getStudentId(), list);
			}
			list.add(cource);
		}
	}


	@Override
	public Student enrollStudent(Student student) throws EnrollmentException {
		synchronized (studentMap) {
			if (student.getStudentId() == 0) {
				student.setStudentId(studentMap.size() + 1);
			}
			studentMap.put(student.getStudentId(), student);
			return student;
		}
	}
}

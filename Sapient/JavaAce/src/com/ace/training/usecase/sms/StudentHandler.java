package com.ace.training.usecase.sms;

import com.ace.training.usecase.sms.notification.NotificationService;
import com.ace.training.usecase.sms.payment.IPayment;
import com.ace.training.usecase.sms.payment.PaymentStatus;

public class StudentHandler implements IStudent, IEnrollStudent {
	private final IPayment payment;
	private final NotificationService notificationService;
	private final CourseValidator courseValidator;

	public StudentHandler(IPayment payment,
			NotificationService notificationService,
			CourseValidator courseValidator) {
		this.payment = payment;
		this.notificationService = notificationService;
		this.courseValidator = courseValidator;
	}

	@Override
	public void enrollCourse(Student student, Course course)
			throws EnrollmentException {
		if (!courseValidator.isElegible(student, course)) {
			throw new EnrollmentException(
					"Student is not eligible to enroll this course: "
							+ course.getName());
		}
		//Payment
		PaymentStatus paymentStatus = payment.doPayment(course.getCost());
		if(!paymentStatus.isSuccess()){
			notificationService.notifyUser(student);
		}

	}

	@Override
	public void enrollActivity(Student student, Course activity)
			throws EnrollmentException {
	}

	@Override
	public Student enrollStudent(Student student) throws EnrollmentException {
		return null;
	}

}

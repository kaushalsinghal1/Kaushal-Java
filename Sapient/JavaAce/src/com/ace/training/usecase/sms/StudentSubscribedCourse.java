package com.ace.training.usecase.sms;

import java.util.Date;

import com.ace.training.usecase.sms.payment.PaymentStatus;

public class StudentSubscribedCourse {

	private Date subscriptionDate;
	private PaymentStatus paymentStatus;
	private int discount;
	private Student student;
	private Course course;

	public StudentSubscribedCourse(Student student, Course course) {
		this.student = student;
		this.course = course;
		subscriptionDate = new Date();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}

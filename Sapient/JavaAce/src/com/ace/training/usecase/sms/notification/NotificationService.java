package com.ace.training.usecase.sms.notification;

import com.ace.training.usecase.sms.Student;

public class NotificationService {
	private EmailNotifier emailNotifier;
	private SMSNotifier smsNotifier;

	public NotificationService(EmailNotifier emailNotifier,
			SMSNotifier smsNotifier) {
		this.emailNotifier = emailNotifier;
		this.smsNotifier = smsNotifier;
	}

	public boolean notifyUser(Student student) {
		sendSMS("", student.getMobileNo());
		sendEMail("", "", new String[] { student.getEmail() });
		return true;
	}

	private boolean sendSMS(String maessage, String mobile) {
		return smsNotifier.sendSMS(maessage, mobile);
	}

	private boolean sendEMail(String subject, String message, String[] toEmail) {
		return emailNotifier.sendEMail(subject, message, toEmail);

	}

}

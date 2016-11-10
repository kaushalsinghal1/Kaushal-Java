package com.ace.training.usecase.sms.notification;

import com.ace.training.usecase.sms.Student;

public class NotificationService implements IUserNotifier {

	@Override
	public boolean notifyUser(Student student) {
		sendSMS("", student.getMobileNo());
		sendEMail("", "", new String[] { student.getEmail() });
		return true;
	}

	private boolean sendSMS(String maessage, String mobile) {
		// Sms sending code
		return true;
	}

	private boolean sendEMail(String subject, String message, String[] toEmail) {
		// email sending code
		return true;

	}

}

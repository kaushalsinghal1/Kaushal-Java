package com.ace.training.usecase.sms.notification;


public interface EmailNotifier {
	boolean sendEMail(String subject, String message, String [] toEmail);
}

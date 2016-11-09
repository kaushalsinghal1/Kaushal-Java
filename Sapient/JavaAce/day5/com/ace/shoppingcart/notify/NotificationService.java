package com.ace.shoppingcart.notify;

import com.ace.shoppingcart.Order;

public class NotificationService {
	private EmailNotifier emailNotifier;
	private SMSNotifier smsNotifier;

	public NotificationService(EmailNotifier emailNotifier,
			SMSNotifier smsNotifier) {
		this.emailNotifier = emailNotifier;
		this.smsNotifier = smsNotifier;
	}

	public boolean notifyCustomer(Order order) {
		sendEMail(order);
		sendSMS(order);
		return true;
	}

	private boolean sendEMail(Order order) {
		return emailNotifier.sendEMail(order);
	}

	private boolean sendSMS(Order order) {
		return smsNotifier.sendSMS(order);
	}

}

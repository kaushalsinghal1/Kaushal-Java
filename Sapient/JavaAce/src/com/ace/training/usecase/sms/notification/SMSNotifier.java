package com.ace.training.usecase.sms.notification;

import com.ace.shoppingcart.Order;

public interface SMSNotifier {
	boolean sendSMS(String maessage, String mobile);
}

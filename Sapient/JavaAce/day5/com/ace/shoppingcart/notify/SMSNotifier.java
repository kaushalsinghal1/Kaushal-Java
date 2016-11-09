package com.ace.shoppingcart.notify;

import com.ace.shoppingcart.Order;

public interface SMSNotifier {
	boolean sendSMS(Order order);
}

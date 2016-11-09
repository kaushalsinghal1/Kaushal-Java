package com.ace.shoppingcart.notify;

import com.ace.shoppingcart.Order;

public interface EmailNotifier {
	boolean sendEMail(Order order);
}

package com.ace.shoppingcart.payment;

import com.ace.shoppingcart.Order;

public interface Payment {
	PaymentStatus doPayment(Order order);

}

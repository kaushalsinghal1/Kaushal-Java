package com.ace.shoppingcart;

import com.ace.training.usecase.sms.payment.PaymentStatus;

public class Order {
	private PaymentStatus paymentStatus;

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	

}

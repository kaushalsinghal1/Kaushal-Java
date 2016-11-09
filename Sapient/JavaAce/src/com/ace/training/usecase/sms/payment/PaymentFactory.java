package com.ace.training.usecase.sms.payment;

public class PaymentFactory {
	private static PaymentFactory instance;

	public static PaymentFactory getInstance() {
		return instance;
	}

	private PaymentFactory() {
	}

	public IPayment getPayment(String type) {
		if ("wallet".equals(type)) {
			return new MobileWaletPayment();
		} else if ("creditcard".equals(type)) {
			return new CreditCardPayment();
		}

		return null;
	}

	public Object readResolve() {
		return instance;

	}
}

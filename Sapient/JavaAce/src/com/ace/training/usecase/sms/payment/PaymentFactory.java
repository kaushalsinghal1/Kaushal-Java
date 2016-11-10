package com.ace.training.usecase.sms.payment;

public class PaymentFactory {
	private static PaymentFactory instance;

	public static PaymentFactory getInstance() {
		if(instance == null){
			synchronized (PaymentFactory.class) {
				if(instance == null){
					instance = new PaymentFactory();
				}
			}
		}
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

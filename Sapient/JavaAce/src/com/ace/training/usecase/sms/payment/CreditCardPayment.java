package com.ace.training.usecase.sms.payment;


public class CreditCardPayment implements IPayment {

	@Override
	public PaymentStatus doPayment(int amount) {
		//Dummmy ttaus
		PaymentStatus paymentStatus = new PaymentStatus();
		paymentStatus.setAmount(amount);
		paymentStatus.setStatus("SUCCESS");
		paymentStatus.setTransactionId("12222");
		return paymentStatus;
	}

}

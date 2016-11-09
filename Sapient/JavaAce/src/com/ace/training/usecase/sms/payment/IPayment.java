package com.ace.training.usecase.sms.payment;


public interface IPayment {
	PaymentStatus doPayment(int amount);

}
